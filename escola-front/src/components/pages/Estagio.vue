<template>
  <div>
    <b-card no-body>
      <b-tabs card>
        <b-tab title="Estágios" active>
          <b-card-text>Cadastro de estágios</b-card-text>
          <b-button v-b-modal.modal-prevent-closing id="botaoNovo">Novo</b-button>
          <b-pagination
            v-model="currentPage"
            :total-rows="rows"
            :per-page="perPage"
            aria-controls="my-table"
          ></b-pagination>
          <b-table
            id="my-table"
            :items="estagios"
            :per-page="perPage"
            :current-page="currentPage"
            @row-dblclicked="editar"
            small
          ></b-table>
          <b-modal
            id="modal-prevent-closing"
            ref="modal"
            title="Nova estágio"
            @hidden="resetModal"
            @ok="handleSubmit"
          >
            <form ref="form" @submit.stop.prevent="handleSubmit">
              <b-form-group label="Nome" label-for="nome">
                <b-form-input id="nome" v-model="estagio.nome" required maxlength="45"></b-form-input>
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
      return this.estagios.length;
    },
  },

  data() {
    return {
      estagio: {},
      estagios: [],
      perPage: 10,
      currentPage: 1,
    };
  },

  created() {
    this.buscarEstagios();
  },

  methods: {
    buscarEstagios() {
      this.$http
        .get(process.env.VUE_APP_BASE_URI + 'estagio')
        .then(resposta => resposta.json())
        .then(
          estagios => (this.estagios = estagios),
          erro =>
            this.$bvToast.toast(
              'Erro ao buscar os estágios' + erro.body.message,
              this.$toastErro
            )
        );
    },

    resetModal() {
      this.buscarEstagios();
      this.estagio = {};
    },

    editar(estagio) {
      this.estagio = estagio;
      this.$nextTick(() => {
        this.$refs.modal.show();
      });
    },

    atualizar() {
      this.$http
        .put(process.env.VUE_APP_BASE_URI + 'estagio', this.estagio)
        .then(
          sucesso =>
            this.$bvToast.toast(
              'Estágio atualizada com sucesso',
              this.$toastInfo
            ),
          erro => this.$bvToast.toast(erro.body.message, this.$toastInfo)
        );
      this.$nextTick(() => {
        this.$refs.modal.hide();
      });
    },

    inserir() {
      this.$http
        .post(process.env.VUE_APP_BASE_URI + 'estagio', this.estagio)
        .then(
          sucesso =>
            this.$bvToast.toast(
              'Estágio inserido com sucesso',
              this.$toastInfo
            ),
          erro => this.$bvToast.toast(erro.body.message, this.$toastInfo)
        );
      this.$nextTick(() => {
        this.$refs.modal.hide();
      });
    },

    handleSubmit() {
      if (this.estagio.idEstagio) this.atualizar();
      else this.inserir();
    },
  },
};
</script>


<style scoped>
</style>



