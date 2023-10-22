import { NewUser } from "../../models/User";
import customAxios from "../AxiosInterceptor/AxiosInterceptor";

class UserService {
  register(userData: NewUser) {
    return customAxios.post(`/user/register`, userData);
  }

  login(userData: { username: string; password: string }) {
    return customAxios.post(`/user/login`, userData);
  }

  getAllConsumers() {
    return customAxios.get(`/user`);
  }

  blockUser(id: number) {
    return customAxios.get(`user/block/${id}`);
  }
}

export default new UserService();
