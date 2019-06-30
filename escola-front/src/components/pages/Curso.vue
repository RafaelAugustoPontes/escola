<template>
  <div>
    <b-card no-body>
      <b-tabs card>
        <b-tab title="Cursos" active>
          <b-card-text>Cadastro de cursos</b-card-text>
          <b-button v-b-modal.modal-prevent-closing id="botaoNovo">Nova</b-button>
          <b-pagination
            v-model="currentPage"
            :total-rows="rows"
            :per-page="perPage"
            aria-controls="my-table"
          ></b-pagination>
          <b-table
            id="my-table"
            :items="cursos"
            :per-page="perPage"
            :current-page="currentPage"
            @row-dblclicked="editar"
            small
          ></b-table>
          <b-modal
            id="modal-prevent-closing"
            ref="modal"
            title="Nova curso"
            @hidden="resetModal"
            @ok="handleSubmit"
          >
            <form ref="form" @submit.stop.prevent="handleSubmit">
              <b-form-group label="Nome" label-for="nome">
                <b-form-input id="nome" v-model="curso.nome" required maxlength="45"></b-form-input>
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
            return this.cursos.length
        },
    },

    data() {
        return {
            curso: {},
            cursos: [],
            perPage: 10,
            currentPage: 1,
        }
    },

    created() {
        this.buscarCursos()
    },

    methods: {
        buscarCursos() {
            this.$http
                .get(process.env.VUE_APP_BASE_URI + 'curso')
                .then(resposta => resposta.json())
                .then(
                    cursos => (this.cursos = cursos),
                    erro =>
                        this.$bvToast.toast(
                            'Erro ao buscar os cursos' + erro.body.message,
                            this.$toastErro
                        )
                )
        },

        resetModal() {
            this.buscarCursos()
            this.curso = {}
        },

        editar(curso) {
            this.curso = curso
            this.$nextTick(() => {
                this.$refs.modal.show()
            })
        },

        atualizarCurso() {
          this.$http
                .put(process.env.VUE_APP_BASE_URI + 'curso', this.curso)
                .then(
                    sucesso => this.$bvToast.toast('Curso atualizada com sucesso', this.$toastInfo),
                    erro =>
                        this.$bvToast.toast(erro.body.message, this.$toastInfo)
                )
            this.$nextTick(() => {
                this.$refs.modal.hide()
            })
        },

        inserirCurso() {
          this.$http
                .post(process.env.VUE_APP_BASE_URI + 'curso', this.curso)
                .then(
                    sucesso => this.$bvToast.toast('Curso inserido com sucesso', this.$toastInfo),
                    erro =>
                        this.$bvToast.toast(erro.body.message, this.$toastInfo)
                )
            this.$nextTick(() => {
                this.$refs.modal.hide()
            })
        },

        handleSubmit() {
            if (this.curso.idCurso) this.atualizarCurso()
            else this.inserirCurso()
        },

    },
}
</script>


<style scoped>
</style>



