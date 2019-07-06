<template>
  <div>
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
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
</style>

