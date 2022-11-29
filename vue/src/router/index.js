import { createRouter, createWebHistory } from 'vue-router'
import TableView from '@/views/TableView.vue'
import EditorView from "@/views/EditorView";
import GraphView from "@/views/GraphView";
import HomeView from "@/views/HomeView";

const routes = [
  {
    path: '/table',
    name: 'table',
    component: TableView
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
  },
  {
    path: '/',
    name: 'Home',
    component: HomeView
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
