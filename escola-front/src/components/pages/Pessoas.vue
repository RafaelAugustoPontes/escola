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
            </form>
          </b-modal>
        </b-tab>
      </b-tabs>
    </b-card>
  </div>
</template>

<script>
import { mask } from "vue-the-mask";
export default {
  directives: { mask },
  computed: {
    rows() {
      return this.pessoas.length;
    }
  },
  data() {
    return {
      colunas: ["Nome", "CPF", "Matrícula", "Data de nascimento"],
      pessoa: {},
      pessoas: [],
      perPage: 5,
      currentPage: 1
    };
  },
  created() {
    this.buscarPessoas();
  },
  methods: {
    editar(record, index) {
      alert(record.nome);
    },
    buscarPessoas() {
      this.$http
        .get("http://localhost:8080/pessoa")
        .then(resposta => resposta.json())
        .then(
          pessoas => (this.pessoas = pessoas),
          erro => alert("Erro ao buscar as pessoas" + erro)
        );
    },
    checkFormValidity() {
      const valid = this.$refs.form.checkValidity();
      this.nameState = valid ? "valid" : "invalid";
      return valid;
    },
    resetModal() {
      this.pessoa = {};
    },
    handleOk(bvModalEvt) {
      bvModalEvt.preventDefault();
      this.handleSubmit();
    },
    handleSubmit() {
      if (!this.checkFormValidity()) {
        return;
      }

      this.$http
        .post("http://localhost:8080/pessoa", this.pessoa)
        .then(this.buscarPessoas())
        .catch(error => alert(error));

      this.$nextTick(() => {
        this.$refs.modal.hide();
      });
    }
  }
};
</script>

<style scoped>
</style>


