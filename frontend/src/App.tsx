import { useCallback, useEffect, useState } from "react";
import { listPeople } from "./api";
import { PersonDetail } from "./components/PersonDetail";
import { RegisterForm } from "./components/RegisterForm";
import { SessionBar } from "./components/SessionBar";
import type { Person } from "./types";

const TOKEN_KEY = "quipux.token";
const USER_KEY = "quipux.user";

export function App() {
  const [token, setToken] = useState<string | null>(() => sessionStorage.getItem(TOKEN_KEY));
  const [username, setUsername] = useState(() => sessionStorage.getItem(USER_KEY) ?? "");
  const [people, setPeople] = useState<Person[]>([]);
  const [selectedId, setSelectedId] = useState<string | null>(null);
  const [listError, setListError] = useState<string | null>(null);

  const refresh = useCallback(async () => {
    setPeople(await listPeople());
    setListError(null);
  }, []);

  useEffect(() => {
    refresh().catch((err: unknown) => {
      setListError(err instanceof Error ? err.message : "Falha ao listar");
    });
  }, [refresh]);

  function handleLogin(nextToken: string, nextUser: string) {
    sessionStorage.setItem(TOKEN_KEY, nextToken);
    sessionStorage.setItem(USER_KEY, nextUser);
    setToken(nextToken);
    setUsername(nextUser);
  }

  function handleLogout() {
    sessionStorage.removeItem(TOKEN_KEY);
    sessionStorage.removeItem(USER_KEY);
    setToken(null);
    setUsername("");
  }

  const selected = people.find((person) => person.id === selectedId) ?? null;

  return (
    <main className="mx-auto max-w-5xl px-4 py-8">
      <header className="flex flex-col gap-4 border-b border-line pb-4 sm:flex-row sm:items-end sm:justify-between">
        <div>
          <p className="text-xs uppercase tracking-[0.14em] text-pine">Prova técnica</p>
          <h1 className="text-3xl font-semibold">Cadastro de pessoas</h1>
          <p className="mt-1 max-w-xl text-sm text-ink/70">
            Cadastro e consulta são públicos. Exclusão e nacionalidade pedem sessão, porque apagam dado e chamam uma API externa.
          </p>
        </div>
        <SessionBar username={username} onLogin={handleLogin} onLogout={handleLogout} />
      </header>

      <div className="mt-6 grid gap-6 lg:grid-cols-[320px_1fr]">
        <RegisterForm
          onCreated={(person) => {
            setSelectedId(person.id);
            refresh().catch((err: unknown) => setListError(err instanceof Error ? err.message : "Falha ao listar"));
          }}
        />
        <section className="space-y-4">
          <h2 className="text-lg font-semibold">Pessoas</h2>
          {listError ? (
            <p role="alert" className="text-sm text-danger">
              {listError}
            </p>
          ) : null}
          {people.length === 0 ? <p className="text-sm text-ink/70">Nenhuma pessoa cadastrada.</p> : null}
          <ul className="divide-y divide-line overflow-hidden rounded-xl border border-line bg-card">
            {people.map((person) => (
              <li key={person.id}>
                <button
                  type="button"
                  className="flex w-full items-center justify-between px-4 py-3 text-left hover:bg-paper"
                  onClick={() => setSelectedId(person.id)}
                >
                  <span>
                    {person.nome} {person.sobrenome}
                    <span className="mt-0.5 block text-xs text-ink/60">{person.email}</span>
                  </span>
                  <span className="font-mono text-xs text-ink/50">{person.documento}</span>
                </button>
              </li>
            ))}
          </ul>
          {selected ? (
            <PersonDetail
              key={selected.id}
              person={selected}
              token={token}
              onDeleted={() => {
                setSelectedId(null);
                refresh().catch((err: unknown) => setListError(err instanceof Error ? err.message : "Falha ao listar"));
              }}
            />
          ) : null}
        </section>
      </div>
      <p className="mt-8 text-xs text-ink/50">Demo: avaliador / quipux-demo. Swagger em http://localhost:8080/swagger-ui.html</p>
    </main>
  );
}
