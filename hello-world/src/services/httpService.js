import axios from 'axios';
//import authService from "./auth.service";
import router from '../router';

let config = {
	baseURL: 'http://keepr.pseudot.xyz'
};

/*
/** Creating the instance for axios */
const httpClient = axios.create(config);

/** Auth token interceptors */
/*const authInterceptor = config => {
  config.headers.Authorization = `Bearer ${authService.getToken()}`;
  return config;
};
*/


/** Adding the request interceptors */
//httpClient.interceptors.request.use(authInterceptor);
/** Adding the response interceptors */
httpClient.interceptors.response.use(
	response => {
		/** TODO: Add any response interceptors */
		return response;
	},
	error => {
		if (error.response.status === 401) {
			router.push({name: 'login'})
		}
		/** TODO: Do something with response error */
		return Promise.reject(error);
	}
);

export default httpClient;
