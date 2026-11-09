import { useState } from "react";
import { deletePerson, findNationality } from "../api";
import { ApiError, type Nationality, type Person } from "../types";

interface PersonDetailProps {
  person: Person;
  token: string | null;
  onDeleted: () => void;
}

export function PersonDetail({ person, token, onDeleted }: PersonDetailProps) {
  const [nationality, setNationality] = useState<Nationality | null>(null);
  const [error, setError] = useState<string | null>(null);
  const [confirming, setConfirming] = useState(false);
  const [pending, setPending] = useState(false);

  async function consult() {
    if (!token) {
      setError("Entre com a sessão de demonstração para consultar a nacionalidade.");
      return;
    }
    setPending(true);
    setError(null);
    try {
      setNationality(await findNationality(person.id, token));
    } catch (err) {
      setError(err instanceof ApiError ? err.message : "Falha na consulta");
    } finally {
      setPending(false);
    }
  }

  async function remove() {
    if (!token) {
      setError("Entre com a sessão de demonstração para excluir.");
      return;
    }
    setPending(true);
    setError(null);
    try {
      await deletePerson(person.id, token);
      onDeleted();
    } catch (err) {
      setError(err instanceof ApiError ? err.message : "Falha ao excluir");
    } finally {
      setPending(false);
      setConfirming(false);
    }
  }

  return (
    <section className="rounded-xl border border-line bg-card p-4">
      <h2 className="text-lg font-semibold">
        {person.nome} {person.sobrenome}
      </h2>
      <dl className="mt-3 grid gap-2 text-sm sm:grid-cols-2">
        <div>
          <dt className="text-ink/60">Documento</dt>
          <dd>{person.documento}</dd>
        </div>
        <div>
          <dt className="text-ink/60">E-mail</dt>
          <dd>{person.email}</dd>
        </div>
        <div className="sm:col-span-2">
          <dt className="text-ink/60">Id</dt>
          <dd className="break-all font-mono text-xs">{person.id}</dd>
        </div>
      </dl>
      <div className="mt-4 flex flex-wrap gap-2">
        <button type="button" className="rounded-md bg-pine px-3 py-2 text-sm text-white" onClick={consult} disabled={pending}>
          Consultar nacionalidade
        </button>
        {confirming ? (
          <button type="button" className="rounded-md bg-danger px-3 py-2 text-sm text-white" onClick={remove} disabled={pending}>
            Confirmar exclusão
          </button>
        ) : (
          <button type="button" className="rounded-md border border-danger px-3 py-2 text-sm text-danger" onClick={() => setConfirming(true)}>
            Excluir
          </button>
        )}
      </div>
      {error ? (
        <p role="alert" className="mt-3 text-sm text-danger">
          {error}
        </p>
      ) : null}
      {nationality ? (
        <p className="mt-3 text-sm">
          Nacionalidade estimada: <strong>{nationality.nacionalidade}</strong> ({nationality.codigoNacionalidade}), probabilidade{" "}
          {(nationality.probabilidade * 100).toFixed(1)}%.
        </p>
      ) : null}
    </section>
  );
}
