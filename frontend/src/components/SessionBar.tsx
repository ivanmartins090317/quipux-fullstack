import { FormEvent, useState } from "react";
import { login } from "../api";
import { ApiError } from "../types";

interface SessionBarProps {
  username: string;
  onLogin: (token: string, username: string) => void;
  onLogout: () => void;
}

export function SessionBar({ username, onLogin, onLogout }: SessionBarProps) {
  const [user, setUser] = useState("avaliador");
  const [password, setPassword] = useState("");
  const [error, setError] = useState<string | null>(null);
  const [pending, setPending] = useState(false);

  async function handleSubmit(event: FormEvent) {
    event.preventDefault();
    setPending(true);
    setError(null);
    try {
      const result = await login(user, password);
      onLogin(result.token, user);
      setPassword("");
    } catch (err) {
      setError(err instanceof ApiError ? err.message : "Falha no login");
    } finally {
      setPending(false);
    }
  }

  if (username) {
    return (
      <div className="flex items-center gap-3 text-sm">
        <span className="text-ink/70">Sessão: {username}</span>
        <button type="button" className="underline" onClick={onLogout}>
          Sair
        </button>
      </div>
    );
  }

  return (
    <form className="flex flex-wrap items-end gap-2" onSubmit={handleSubmit}>
      <label className="text-sm">
        Usuário
        <input
          className="mt-1 block w-36 rounded-md border border-line bg-card px-2 py-1"
          value={user}
          onChange={(event) => setUser(event.target.value)}
          autoComplete="username"
        />
      </label>
      <label className="text-sm">
        Senha
        <input
          className="mt-1 block w-36 rounded-md border border-line bg-card px-2 py-1"
          type="password"
          value={password}
          onChange={(event) => setPassword(event.target.value)}
          autoComplete="current-password"
        />
      </label>
      <button
        type="submit"
        disabled={pending}
        className="rounded-md bg-pine px-3 py-2 text-sm text-white disabled:opacity-60"
      >
        Entrar
      </button>
      {error ? <p className="text-sm text-danger">{error}</p> : null}
    </form>
  );
}
