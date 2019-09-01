<template>
  <div>
    <b-modal
      size="xl"
      id="modal-cadastro-turma"
      ref="modal"
      title="Nova turma"
      cancel-title="Cancelar"
      ok-title="Gravar"
      :ok-disabled="!turma || !turma.nome"
      @hidden="resetModal"
      @ok="handleSubmit"
    >
      <form ref="form" @submit.stop.prevent="handleSubmit">
        <b-form-group label="Nome" label-for="nome">
          <b-form-input id="nome" v-model="turma.nome" required maxlength="45"></b-form-input>
        </b-form-group>
        <b-form-group label="Unidade" label-for="Unidade">
          <b-form-select
            :text-field="unidade.nome"
            :options="opcoesUnidade"
            v-model="unidade.idUnidade"
          ></b-form-select>
        </b-form-group>
        <b-form-group label="Curso" label-for="Curso">
          <b-form-select v-model="curso.idCurso" :options="opcoesUnidade"></b-form-select>
        </b-form-group>
        <b-form-group label="Estágio" label-for="Estágio">
          <b-form-select v-model="estagio.idEstagio" :options="opcoesUnidade"></b-form-select>
        </b-form-group>
        <b-form-group label="Professor" label-for="Professor">
          <b-form-select v-model="professor.idPessoa" :options="opcoesUnidade"></b-form-select>
        </b-form-group>
        <b-form-group label="Data início" label-for="dataInicio">
          <b-form-input id="dataInicio" type="date" v-model="turma.dataInicio" required></b-form-input>
        </b-form-group>
        <b-form-group label="Data fim" label-for="dataFim">
          <b-form-input id="dataFim" type="date" v-model="turma.dataFim" required></b-form-input>
        </b-form-group>
        <b-card-group deck>
          <b-card header="Alunos selecionados">
            <b-list-group>
              <b-list-group-item
                v-for="item in alunos"
                :value="item.nome"
                :key="item.idPessoa"
              >{{item.nome}}</b-list-group-item>
            </b-list-group>
          </b-card>
        </b-card-group>
        <div>
          <tabela-generica :itens="opcoesAlunos" :campos="campos" @selecionar="selecionar"></tabela-generica>
        </div>
      </form>
    </b-modal>
  </div>
</template>

<script>
import TabelaGenerica from '../tables/TabelaGenerica';

export default {
  created() {
    this.buscarAlunos();
    this.buscarUnidades();
  },

  props: ['turma'],

  data() {
    return {
      unidade: {},
      curso: {},
      estagio: {},
      professor: {},
      opcoesUnidade: [],
      opcoesEstagios: [],
      opcoesProfessores: [],
      opcoesAlunos: [],
      alunos: [],
      campos: ['nome'],
    };
  },

  components: {
    'tabela-generica': TabelaGenerica,
  },

  methods: {
    resetModal() {
      this.$emit('modalFechada');
    },

    atualizar() {
      this.$http
        .put(process.env.VUE_APP_BASE_URI + 'turma', this.turma)
        .then(
          () =>
            this.$bvToast.toast(
              'Turma atualizada com sucesso',
              this.$toastInfo
            ),
          erro => this.$bvToast.toast(erro.body.message, this.$toastInfo)
        );
      this.fechar();
    },

    inserir() {
      this.$http
        .post(process.env.VUE_APP_BASE_URI + 'turma', this.turma)
        .then(
          () =>
            this.$bvToast.toast('Turma inserida com sucesso', this.$toastInfo),
          erro => this.$bvToast.toast(erro.body.message, this.$toastInfo)
        );
      this.fechar();
    },

    handleSubmit() {
      if (this.turma.idturma) this.atualizar();
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
      this.alunos.forEach(aluno => {
        if (aluno.idPessoa == item.idPessoa) {
          alunoExiste = true;
        }
      });
      if (!alunoExiste) this.alunos.unshift(item);
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
              'Erro ao buscar os alunos' + erro.body.message,
              this.$toastErro
            )
        );
    },
  },
};
</script>

<style scoped>
</style>

