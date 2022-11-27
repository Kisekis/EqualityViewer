import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import EditorView from "@/views/EditorView";
import GraphView from "@/views/GraphView";

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/editor',
    name: 'editor',
    component: EditorView
  },
  {
    path: '/graph',
    name: 'graph',
    component: GraphView
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
