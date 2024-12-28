export interface Auth {
    email: string;
    password: string;
    role: RoleAuth;
    token: string;
}

export enum RoleAuth {
    CANDIDATE = 'candidate',
    HR = 'hr',
}