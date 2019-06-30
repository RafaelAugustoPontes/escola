<template>
  <div>
    <b-card no-body>
      <b-tabs card>
        <b-tab title="Unidades" active>
          <b-card-text>Cadastro de unidades</b-card-text>
          <b-button v-b-modal.modal-prevent-closing id="botaoNovo">Nova</b-button>
          <b-pagination
            v-model="currentPage"
            :total-rows="rows"
            :per-page="perPage"
            aria-controls="my-table"
          ></b-pagination>
          <b-table
            id="my-table"
            :items="unidades"
            :per-page="perPage"
            :current-page="currentPage"
            @row-dblclicked="editar"
            small
          ></b-table>
          <b-modal
            id="modal-prevent-closing"
            ref="modal"
            title="Nova unidade"
            @hidden="resetModal"
            @ok="handleSubmit"
          >
            <form ref="form" @submit.stop.prevent="handleSubmit">
              <b-form-group label="Nome" label-for="nome">
                <b-form-input id="nome" v-model="unidade.nome" required maxlength="45"></b-form-input>
              </b-form-group>
            </form>
          </b-modal>
        </b-tab>
      </b-tabs>
    </b-card>
  </div>
</template>

<script>
export default {
    computed: {
        rows() {
            return this.unidades.length
        },
    },

    data() {
        return {
            unidade: {},
            unidades: [],
            perPage: 10,
            currentPage: 1,
        }
    },

    created() {
        this.buscarUnidades()
    },

    methods: {
        buscarUnidades() {
            this.$http
                .get(process.env.VUE_APP_BASE_URI + 'unidade')
                .then(resposta => resposta.json())
                .then(
                    unidades => (this.unidades = unidades),
                    erro =>
                        this.$bvToast.toast(
                            'Erro ao buscar as unidades' + erro.body.message,
                            this.$toastErro
                        )
                )
        },

        resetModal() {
            console.log('reset')
            this.buscarUnidades()
            this.unidade = {}
        },

        editar(unidade) {
            console.log(unidade)
            this.unidade = unidade
            this.$nextTick(() => {
                this.$refs.modal.show()
            })
        },

        atualizarUnidade() {
          this.$http
                .put(process.env.VUE_APP_BASE_URI + 'unidade', this.unidade)
                .then(
                    sucesso => this.$bvToast.toast('Unidade atualizada com sucesso', this.$toastInfo),
                    erro =>
                        this.$bvToast.toast(erro.body.message, this.$toastInfo)
                )
            this.$nextTick(() => {
                this.$refs.modal.hide()
            })
        },

        inserirUnidade() {
          this.$http
                .post(process.env.VUE_APP_BASE_URI + 'unidade', this.unidade)
                .then(
                    sucesso => this.$bvToast.toast('Unidade inserida com sucesso', this.$toastInfo),
                    erro =>
                        this.$bvToast.toast(erro.body.message, this.$toastInfo)
                )
            this.$nextTick(() => {
                this.$refs.modal.hide()
            })
        },

        handleSubmit() {
            if (this.unidade.idUnidade) this.atualizarUnidade()
            else this.inserirUnidade()
        },

    },
}
</script>


<style scoped>
</style>



