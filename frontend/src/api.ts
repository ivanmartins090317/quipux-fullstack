import { ApiError, type Nationality, type Person, type TokenResponse } from "./types";

const baseUrl = import.meta.env.VITE_API_URL ?? "";

interface RequestOptions {
  method?: string;
  body?: unknown;
  token?: string | null;
}

export async function api<T>(path: string, options: RequestOptions = {}): Promise<T> {
  const headers = new Headers();
  if (options.body !== undefined) {
    headers.set("Content-Type", "application/json");
  }
  if (options.token) {
    headers.set("Authorization", `Bearer ${options.token}`);
  }

  let response: Response;
  try {
    response = await fetch(`${baseUrl}${path}`, {
      method: options.method ?? "GET",
      headers,
      body: options.body !== undefined ? JSON.stringify(options.body) : undefined,
    });
  } catch {
    throw new ApiError(0, "API indisponível. Confira se o backend está na porta 8080.");
  }

  if (response.status === 204) {
    return undefined as T;
  }

  const payload = (await response.json().catch(() => ({}))) as {
    message?: string;
    fields?: Record<string, string>;
  };

  if (!response.ok) {
    throw new ApiError(response.status, payload.message ?? "Falha na requisição", payload.fields);
  }

  return payload as T;
}

export function listPeople() {
  return api<Person[]>("/list");
}

export function registerPerson(body: Omit<Person, "id">) {
  return api<Person>("/registrarName", { method: "POST", body });
}

export function login(username: string, password: string) {
  return api<TokenResponse>("/auth/login", { method: "POST", body: { username, password } });
}

export function findNationality(id: string, token: string) {
  return api<Nationality>(`/findNacionalityByPerson/${id}`, { token });
}

export function deletePerson(id: string, token: string) {
  return api<void>(`/list/${id}`, { method: "DELETE", token });
}
