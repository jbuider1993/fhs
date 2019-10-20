<template>
    <div style="width:100%;height:100%;">
        <mu-circular-progress :size="40" v-if="loading"/>
        <!--<div v-html="html"></div>-->
        <iframe v-bind:src=resUrl width="100%" height="100%" frameborder="0" scrolling="auto" allowfullscreen="true"
                webkitallowfullscreen="true"
                mozallowfullscreen="true"></iframe>

    </div>
</template>
<style>

</style>
<script>
    // import {FetchGetHtml} from "@/api/public";

    export default {
        name: "htmlPanel",
        // 使用时请使用 :url.sync=""传值
        props: {
            url: {
                required: true
            }
        },
        data() {
            return {
                loading: false,
                html: '',
                resUrl: ''
            }
        },
        watch: {
            url(value) {
                // this.load(value)
                this.resUrl = value;
            }
        },
        mounted() {
            //this.load(this.url)

        },
        methods: {
            // load(url) {
            //     if (url && url.length > 0) {
            //         // 加载中
            //         this.loading = true
            //         let param = {
            //             //accept: 'text/html, text/plain'
            //         }
            //         FetchGetHtml(url, param).then((response) => {
            //             this.loading = false
            //             // 处理HTML显示
            //             this.html = response
            //         }).catch(() => {
            //             this.loading = false
            //             this.html = '加载失败'
            //         })
            //     }
            // }
        }
    }
</script>