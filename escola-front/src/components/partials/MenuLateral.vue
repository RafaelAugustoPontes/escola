<template>
    <div>
        <b-nav vertical pills fill>
            <b-nav-item>Home</b-nav-item>
            <b-nav-item-dropdown
                id="my-nav-dropdown"
                text="Dropdown"
                toggle-class="nav-link-custom"
                right
            >
                <b-dropdown-item>one</b-dropdown-item>
                <b-dropdown-item>two</b-dropdown-item>
                <b-dropdown-divider></b-dropdown-divider>
                <b-dropdown-item>three</b-dropdown-item>
            </b-nav-item-dropdown>
            <b-nav-item>Another Link</b-nav-item>
        </b-nav>
    </div>
</template>
<script>
export default {
    
}
</script>

<style scoped>

</style>

