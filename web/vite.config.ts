import {defineConfig} from "vite"
import {viteStaticCopy} from 'vite-plugin-static-copy'

export default defineConfig({
    build: {
        outDir: "../src/main/resources/static",
        rollupOptions: {
            output: {
                assetFileNames: "assets/[name][extname]",
                chunkFileNames: "assets/[name].js",
                entryFileNames: "assets/[name].js",
            },
        },
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
