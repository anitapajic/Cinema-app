import axios from "axios";

const customAxios = axios.create({
  baseURL: "http://localhost:8085/api",
});

customAxios.interceptors.request.use(
  (config) => {
    const storedData = localStorage.getItem("user");
    if (storedData) {
      const userData = JSON.parse(storedData);
      if (userData && userData.token) {
        config.headers["Authorization"] = "Bearer " + userData.token;
      }
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

customAxios.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response.status === 401) {
      throw new Error("UNAUTHORIZED");
    }
    return Promise.reject(error);
  }
);

export default customAxios;
