import {createRouter, createWebHistory} from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/LoginView.vue'
import RegisterView from '../views/RegisterView.vue'
import DefaultLayout from "../layouts/DefaultLayout";
import AuthLayout from "../layouts/AuthLayout";

const routes = [
	{
		path: '/',
		name: 'Default',
		redirect: '/home',
		component: DefaultLayout,
		children: [
			{
				path: 'home',
				name: 'home',
				component: HomeView
			}
		]
	},
	{
		path: '/auth',
		name: 'Auth',
		component: AuthLayout,
		children: [
			{
				path: 'login',
				name: 'login',
				component: LoginView
			},
			{
				path: 'register',
				name: 'register',
				component: RegisterView
			}
		]
	},
	{
		path: '/login',
		redirect: '/auth/login'
	},
	{
		path: '/register',
		redirect: '/auth/register'
	},
]

const router = createRouter({
	history: createWebHistory(),
	routes
})

export default router
