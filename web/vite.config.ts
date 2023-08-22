import {defineConfig} from "vite"
import {viteStaticCopy} from 'vite-plugin-static-copy'

export default defineConfig({
    build: {
        outDir: "../src/main/resources/static",
        emptyOutDir: true,
    },
    plugins: [
        viteStaticCopy({
            targets: [{
                src: 'node_modules/@shoelace-style/shoelace/dist/assets/*',
                dest: 'shoelace',
            }]
        })
    ],
})
