export default {
  props: ['title', 'id'],
  methods: {
    close() {
      this.$emit('close', this.id);
    },
  },
};
