import React from "react";
import Role from "./Enums/Role";

export interface User {
  id: number;
  name: string;
  username: string;
  password: string;
  dateOfBirth: string;
  role: Role;
  blocked: boolean;
  verified: boolean;
}

export interface NewUser {
  name: string;
  username: string;
  password: string;
  dateOfBirth: string;
  blocked?: boolean;
  verified?: boolean;
}
