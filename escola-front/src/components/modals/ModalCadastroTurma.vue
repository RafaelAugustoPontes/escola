<template>
  <div>
    <b-modal
      size="xl"
      id="modal-cadastro-turma"
      ref="modal"
      title="Nova turma"
      cancel-title="Cancelar"
      ok-title="Gravar"
      :ok-disabled="!turma || !turma.nome || !turma.dataInicio 
      || !turma.dataFim || !turma.turno || !turma.unidade.idUnidade
      || !turma.curso.idCurso || !turma.estagio.idEstagio || !turma.professor.idPessoa
      || !turma.alunos.length > 0"
      @hidden="resetModal"
      @ok="handleSubmit"
    >
      <form ref="form" @submit.stop.prevent="handleSubmit">
        <app-loading :isLoading="isLoading"></app-loading>
        <b-form-group label="Nome" label-for="nome">
          <b-form-input id="nome" v-model="turma.nome" required maxlength="45"></b-form-input>
        </b-form-group>
        <b-form-group label="Data início" label-for="dataInicio">
          <b-form-input id="dataInicio" type="date" v-model="turma.dataInicio" required></b-form-input>
        </b-form-group>
        <b-form-group label="Data fim" label-for="dataFim">
          <b-form-input id="dataFim" type="date" v-model="turma.dataFim" required></b-form-input>
        </b-form-group>
        <b-form-group label="Turno" label-for="select-turno">
          <b-form-select id="select-turno" :options="opcoesTurno" v-model="turma.turno"></b-form-select>
        </b-form-group>
        <b-form-group label="Unidade" label-for="select-unidade">
          <b-form-select
            id="select-unidade"
            :options="opcoesUnidade"
            v-model="turma.unidade.idUnidade"
          ></b-form-select>
        </b-form-group>
        <b-form-group label="Curso" label-for="Curso">
          <b-form-select :options="opcoesCursos" v-model="turma.curso.idCurso"></b-form-select>
        </b-form-group>
        <b-form-group label="Estágio" label-for="Estágio">
          <b-form-select v-model="turma.estagio.idEstagio" :options="opcoesEstagios"></b-form-select>
        </b-form-group>
        <b-form-group label="Professor" label-for="Professor">
          <b-form-select v-model="turma.professor.idPessoa" :options="opcoesProfessores"></b-form-select>
        </b-form-group>
        <b-card-group deck>
          <b-card header="Alunos disponíveis">
            <tabela-generica :itens="opcoesAlunos" :campos="campos" @selecionar="selecionar"></tabela-generica>
          </b-card>
        </b-card-group>
        <br />
        <b-card-group deck>
          <b-card header="Alunos selecionados">
            <tabela-generica :itens="turma.alunos" :campos="campos" @editar="remover"></tabela-generica>
          </b-card>
        </b-card-group>
      </form>
    </b-modal>
  </div>
</template>

<script>
import TabelaGenerica from '../tables/TabelaGenerica';
import AppLoading from '../partials/app-loading/app-loading.vue';

export default {
  created() {
    this.buscarAlunos();
    this.buscarUnidades();
    this.buscarCursos();
    this.buscarEstagios();
    this.buscarProfessores();
    this.buscarTurnos();
  },

  props: ['turma'],

  data() {
    return {
      opcoesUnidade: [],
      opcoesCursos: [],
      opcoesEstagios: [],
      opcoesProfessores: [],
      opcoesAlunos: [],
      opcoesTurno: [],
      campos: ['nome'],
      isLoading: false,
    };
  },

  components: {
    'tabela-generica': TabelaGenerica,
    'app-loading': AppLoading,
  },

  methods: {
    resetModal() {
      this.$emit('modalFechada');
    },

    atualizar() {
      this.isLoading = true;
      this.$http.put(process.env.VUE_APP_BASE_URI + 'turma', this.turma).then(
        () => {
          this.$bvToast.toast('Turma atualizada com sucesso', this.$toastInfo);
          this.isLoading = false;
          this.fechar();
        },
        erro => {
          this.$bvToast.toast(erro.body.message, this.$toastInfo);
          this.isLoading = false;
        }
      );
    },

    inserir() {
      this.isLoading = true;
      this.$http.post(process.env.VUE_APP_BASE_URI + 'turma', this.turma).then(
        () => {
          this.$bvToast.toast('Turma inserida com sucesso', this.$toastInfo);
          this.isLoading = false;
          this.fechar();
        },
        erro => {
          this.$bvToast.toast(erro.body.message, this.$toastInfo);
          this.isLoading = false;
        }
      );
    },

    handleSubmit(event) {
      event.preventDefault();
      if (this.turma.idTurma) this.atualizar();
      else this.inserir();
    },

    abrir() {
      this.$refs.modal.show();
    },

    fechar() {
      this.$refs.modal.hide();
    },

    selecionar(item) {
      let alunoExiste = false;
      this.turma.alunos.forEach(aluno => {
        if (aluno.idPessoa == item.idPessoa) {
          alunoExiste = true;
        }
      });
      if (!alunoExiste) this.turma.alunos.unshift(item);
    },
    remover(item) {
      if (this.turma.idTurma) {
        this.$bvToast.toast(
          'Não é possível remover alunos de uma turma em andamento',
          this.$toastInfo
        );
      } else {
        let posicao = 0;
        for (let i = 0; i < this.turma.alunos.length; i++) {
          if (this.turma.alunos[i].idPessoa == item.idPessoa) {
            this.turma.alunos.splice(i, 1);
          }
        }
      }
    },

    buscarAlunos() {
      this.$http
        .get(process.env.VUE_APP_BASE_URI + 'pessoa/alunos')
        .then(resposta => resposta.json())
        .then(
          opcoesAlunos => (this.opcoesAlunos = opcoesAlunos),
          erro =>
            this.$bvToast.toast(
              'Erro ao buscar os alunos' + erro.body.message,
              this.$toastErro
            )
        );
    },
    buscarUnidades() {
      this.$http
        .get(process.env.VUE_APP_BASE_URI + 'unidade')
        .then(resposta => resposta.json())
        .then(
          opcoesUnidade => (this.opcoesUnidade = opcoesUnidade),
          erro =>
            this.$bvToast.toast(
              'Erro ao buscar as unidades' + erro.body.message,
              this.$toastErro
            )
        );
    },
    buscarCursos() {
      this.$http
        .get(process.env.VUE_APP_BASE_URI + 'curso')
        .then(resposta => resposta.json())
        .then(
          opcoesCursos => (this.opcoesCursos = opcoesCursos),
          erro =>
            this.$bvToast.toast(
              'Erro ao buscar os cursos' + erro.body.message,
              this.$toastErro
            )
        );
    },
    buscarEstagios() {
      this.$http
        .get(process.env.VUE_APP_BASE_URI + 'estagio')
        .then(resposta => resposta.json())
        .then(
          opcoesEstagios => (this.opcoesEstagios = opcoesEstagios),
          erro =>
            this.$bvToast.toast(
              'Erro ao buscar os estágios' + erro.body.message,
              this.$toastErro
            )
        );
    },
    buscarProfessores() {
      this.$http
        .get(process.env.VUE_APP_BASE_URI + 'pessoa/professores')
        .then(resposta => resposta.json())
        .then(
          opcoesProfessores => (this.opcoesProfessores = opcoesProfessores),
          erro =>
            this.$bvToast.toast(
              'Erro ao buscar os professores' + erro.body.message,
              this.$toastErro
            )
        );
    },
    buscarTurnos() {
      this.$http
        .get(process.env.VUE_APP_BASE_URI + 'turma/turnos')
        .then(resposta => resposta.json())
        .then(
          opcoesTurno => (this.opcoesTurno = opcoesTurno),
          erro =>
            this.$bvToast.toast(
              'Erro ao buscar os turnos' + erro.body.message,
              this.$toastErro
            )
        );
    },
  },
};
</script>

<style scoped>
</style>

