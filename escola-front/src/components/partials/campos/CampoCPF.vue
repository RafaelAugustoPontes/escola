<<template>
    <div>
        <the-mask :mask="['###.###.###-##']" v-model="cpf" />
    </div>
</template>

<script>
import {TheMask} from 'vue-the-mask'
export default {
    props : ['cpf'],
    components : {
        TheMask
    }
    
}
</script>

<style scoped>
</style>