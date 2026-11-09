export interface Person {
  id: string;
  documento: string;
  nome: string;
  sobrenome: string;
  email: string;
}

export interface Nationality {
  id: string;
  nome: string;
  codigoNacionalidade: string;
  nacionalidade: string;
  probabilidade: number;
}

export interface TokenResponse {
  token: string;
  tokenType: string;
  expiresInSeconds: number;
}

export class ApiError extends Error {
  status: number;
  fields?: Record<string, string>;

  constructor(status: number, message: string, fields?: Record<string, string>) {
    super(message);
    this.status = status;
    this.fields = fields;
  }
}
