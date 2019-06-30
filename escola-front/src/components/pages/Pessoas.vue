<template>
  <div>
    <b-card no-body>
      <b-tabs card>
        <b-tab title="Pessoas" active>
          <b-card-text>Cadastro de pessoas</b-card-text>
          <b-button v-b-modal.modal-prevent-closing>Nova</b-button>
          <b-pagination
            v-model="currentPage"
            :total-rows="rows"
            :per-page="perPage"
            aria-controls="my-table"
          ></b-pagination>
          <b-table
            id="my-table"
            :items="pessoas"
            :per-page="perPage"
            :current-page="currentPage"
            @row-dblclicked="editar"
            small
          ></b-table>

          <b-modal
            size="lg"
            id="modal-prevent-closing"
            ref="modal"
            title="Nova Pessoa"
            @show="resetModal"
            @hidden="resetModal"
            @ok="handleOk"
          >
            <form ref="form" @submit.stop.prevent="handleSubmit">
              <b-form-group label="Nome" label-for="nome">
                <b-form-input id="nome" v-model="pessoa.nome" required maxlength="45"></b-form-input>
              </b-form-group>
              <b-form-group label="CPF" label-for="cpf">
                <b-form-input
                  id="cpf"
                  v-model="pessoa.cpf"
                  required
                  maxlength="14"
                  masked="false"
                  v-mask="'###.###.###-##'"
                ></b-form-input>
              </b-form-group>
              <b-form-group label="Matrícula" label-for="matricula">
                <b-form-input id="matricula" v-model="pessoa.matricula" required maxlength="5"></b-form-input>
              </b-form-group>
              <b-form-group label="Data de nascimento" label-for="dataNascimento">
                <b-form-input
                  id="dataNascimento"
                  type="date"
                  v-model="pessoa.dataNascimento"
                  required
                ></b-form-input>
              </b-form-group>
              <b-form-group label="Telefone" label-for="telefone">
                <b-form-input id="telefone" type="tel" v-model="pessoa.telefone"></b-form-input>
              </b-form-group>
              <b-form-group label="E-mail" label-for="email">
                <b-form-input id="email" type="email" v-model="pessoa.email"></b-form-input>
              </b-form-group>
              <b-form-group label="CEP" label-for="cep">
                <b-form-input
                  id="cep"
                  type="text"
                  v-model="pessoa.cep"
                  @focusout="buscarEndereco()"
                ></b-form-input>
              </b-form-group>
              <b-form-group label="Endereco" label-for="endereco">
                <b-form-input :disabled="enderecoDesabilitado" id="endereco" type="text" v-model="pessoa.endereco"></b-form-input>
              </b-form-group>
              <b-form-group label="Número" label-for="numero">
                <b-form-input id="numero" type="number" v-model="pessoa.numero"></b-form-input>
              </b-form-group>
              <b-form-group label="Bairro" label-for="bairro">
                <b-form-input :readonly="enderecoDesabilitado" id="bairro" type="text" v-model="pessoa.bairro"></b-form-input>
              </b-form-group>
              <b-form-group label="Cidade" label-for="cidade">
                <b-form-input :readonly="enderecoDesabilitado" id="cidade" type="text" v-model="pessoa.cidade"></b-form-input>
              </b-form-group>
            </form>
          </b-modal>
        </b-tab>
      </b-tabs>
    </b-card>
  </div>
</template>

<script>
import { mask } from 'vue-the-mask'
export default {
    directives: { mask },
    computed: {
        rows() {
            return this.pessoas.length
        },
    },
    data() {
        return {
            colunas: ['Nome', 'CPF', 'Matrícula', 'Data de nascimento'],
            
            pessoa: {},
            pessoas: [],
            perPage: 10,
            currentPage: 1,
            enderecoDesabilitado: true,
        }
    },
    created() {
        this.buscarPessoas()
    },
    methods: {
        buscarEndereco() {
            this.$http
                .get('https://viacep.com.br/ws/' + this.pessoa.cep + '/json/')
                .then(resposta => resposta.json())
                .then(
                    endereco => {
                        this.pessoa.endereco = endereco.logradouro
                        this.pessoa.bairro = endereco.bairro
                        this.pessoa.cidade = endereco.localidade
                    },
                    erro =>
                        this.$bvToast.toast(
                            'Erro ao buscar o cep' + erro.body.message,
                            this.$toastErro
                        )
                )
        },
        editar(record, index) {
            alert(record.nome)
        },
        buscarPessoas() {
            this.$http
                .get(process.env.VUE_APP_BASE_URI + 'pessoa')
                .then(resposta => resposta.json())
                .then(
                    pessoas => (this.pessoas = pessoas),
                    erro =>
                        this.$bvToast.toast(
                            'Erro ao buscar as pessoas' + erro.body.message,
                            this.$toastErro
                        )
                )
        },

        resetModal() {
            this.pessoa = {}
        },
        handleOk(bvModalEvt) {
            bvModalEvt.preventDefault()
            this.handleSubmit()
        },
        handleSubmit() {
            this.$http
                .post(process.env.VUE_APP_BASE_URI + 'pessoa', this.pessoa)
                .then(this.buscarPessoas(), erro =>
                    this.$bvToast.toast(erro.body.message, this.$toastInfo)
                )
            this.$nextTick(() => {
                this.$refs.modal.hide()
            })
        },
    },
}
</script>

<style scoped>
</style>


