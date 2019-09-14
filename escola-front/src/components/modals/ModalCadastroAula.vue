<template>
  <div>
    <b-modal
      size="lg"
      id="modal-cadastro-aula"
      ref="modal"
      title="Nova aula"
      cancel-title="Cancelar"
      ok-title="Gravar"
      :ok-disabled="!aula.data || !aula.diario"
      @hidden="resetModal"
      @ok="handleSubmit"
    >
      <loading :active.sync="isLoading" :can-cancel="false" :is-full-page="true"></loading>
      <form ref="form" @submit.stop.prevent="handleSubmit">
        <b-form-group label="Turma" label-for="turma">
          <span id="turma" class="label">{{aula.turma.nome}}</span>
        </b-form-group>
        <b-form-group label="Curso" label-for="curso">
          <span id="curso" class="label">{{aula.turma.curso.nome}}</span>
        </b-form-group>
        <b-form-group label="Unidade" label-for="unidade">
          <span id="unidade" class="label">{{aula.turma.unidade.nome}}</span>
        </b-form-group>
        <b-form-group label="Estágio" label-for="estagio">
          <span id="estagio" class="label">{{aula.turma.estagio.nome}}</span>
        </b-form-group>
        <b-form-group label="Data da aula" label-for="data-aula">
          <b-form-input id="dataNascimento" type="date" v-model="aula.data" required></b-form-input>
        </b-form-group>
        <b-form-group label="Diário da aula" label-for="diario">
          <b-form-textarea id="diario" v-model="aula.diario" rows="3" maxlength="2000"></b-form-textarea>
        </b-form-group>
        <b-card-group deck>
          <b-card header="Alunos que faltaram">
            <tabela-generica :itens="aula.turma.alunos" :campos="campos" @selecionar="selecionar"></tabela-generica>
          </b-card>
        </b-card-group>
        <b-card-group deck>
          <b-card header="Alunos presentes">
            <tabela-generica :itens="aula.alunos" :campos="campos" @editar="remover"></tabela-generica>
          </b-card>
        </b-card-group>
      </form>
    </b-modal>
  </div>
</template>

<script>
import Loading from 'vue-loading-overlay';
import 'vue-loading-overlay/dist/vue-loading.css';
import TabelaGenerica from '../tables/TabelaGenerica';

export default {
  props: ['aula'],

  data() {
    return {
      campos: ['nome'],
      isLoading: false,
    };
  },

  components: {
    'tabela-generica': TabelaGenerica,
    Loading,
  },

  methods: {
    selecionar(item) {
      let alunoExiste = false;
      this.aula.alunos.forEach(aluno => {
        if (aluno.idPessoa == item.idPessoa) {
          alunoExiste = true;
        }
      });
      if (!alunoExiste) this.aula.alunos.unshift(item);
    },
    remover(item) {
      let posicao = 0;
      for (let i = 0; i < this.aula.alunos.length; i++) {
        if (this.aula.alunos[i].idPessoa == item.idPessoa) {
          this.aula.alunos.splice(i, 1);
        }
      }
    },

    resetModal() {
      this.$emit('modalFechada');
    },

    atualizar() {
      this.isLoading = true;
      this.$http.put(process.env.VUE_APP_BASE_URI + 'aula', this.aula).then(
        () => {
          this.$bvToast.toast('Aula atualizada com sucesso', this.$toastInfo);
          this.isLoading = false;
          this.fechar();
        },
        erro => this.$bvToast.toast(erro.body.message, this.$toastInfo)
      );
      this.fechar();
    },

    inserir() {
      this.isLoading = true;
      this.$http.post(process.env.VUE_APP_BASE_URI + 'aula', this.aula).then(
        () => {
          this.$bvToast.toast('Aula inserida com sucesso', this.$toastInfo);
          this.isLoading = false;
          this.fechar();
        },
        erro => this.$bvToast.toast(erro.body.message, this.$toastInfo)
      );
      this.fechar();
    },

    handleSubmit(bvModalEvt) {
      bvModalEvt.preventDefault();
      if (this.aula.idAula) this.atualizar();
      else this.inserir();
    },

    abrir() {
      this.$refs.modal.show();
    },

    fechar() {
      this.$refs.modal.hide();
    },
  },
};
</script>

<style scoped>
.label {
  font-weight: bold;
}
</style>

