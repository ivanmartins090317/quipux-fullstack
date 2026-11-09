import { FormEvent, useState, type ReactNode } from "react";
import { registerPerson } from "../api";
import { ApiError, type Person } from "../types";

interface RegisterFormProps {
  onCreated: (person: Person) => void;
}

const empty = { documento: "", nome: "", sobrenome: "", email: "" };

export function RegisterForm({ onCreated }: RegisterFormProps) {
  const [form, setForm] = useState(empty);
  const [fields, setFields] = useState<Record<string, string>>({});
  const [error, setError] = useState<string | null>(null);
  const [pending, setPending] = useState(false);

  function update(key: keyof typeof empty, value: string) {
    setForm((current) => ({ ...current, [key]: value }));
  }

  async function handleSubmit(event: FormEvent) {
    event.preventDefault();
    setPending(true);
    setError(null);
    setFields({});
    try {
      const created = await registerPerson(form);
      setForm(empty);
      onCreated(created);
    } catch (err) {
      if (err instanceof ApiError) {
        setError(err.message);
        setFields(err.fields ?? {});
      } else {
        setError("Falha ao cadastrar");
      }
    } finally {
      setPending(false);
    }
  }

  return (
    <form className="space-y-3 rounded-xl border border-line bg-card p-4" onSubmit={handleSubmit}>
      <h2 className="text-lg font-semibold">Novo cadastro</h2>
      <Field label="Documento" error={fields.documento}>
        <input
          required
          value={form.documento}
          onChange={(event) => update("documento", event.target.value)}
          placeholder="529.982.247-25"
          className={inputClass}
        />
      </Field>
      <Field label="Nome" error={fields.nome}>
        <input required value={form.nome} onChange={(event) => update("nome", event.target.value)} className={inputClass} />
      </Field>
      <Field label="Sobrenome" error={fields.sobrenome}>
        <input
          required
          value={form.sobrenome}
          onChange={(event) => update("sobrenome", event.target.value)}
          className={inputClass}
        />
      </Field>
      <Field label="E-mail" error={fields.email}>
        <input
          required
          type="email"
          value={form.email}
          onChange={(event) => update("email", event.target.value)}
          className={inputClass}
        />
      </Field>
      {error ? (
        <p role="alert" className="text-sm text-danger">
          {error}
        </p>
      ) : null}
      <button
        type="submit"
        disabled={pending}
        className="w-full rounded-md bg-pine px-3 py-2 text-white hover:bg-pine-dark disabled:opacity-60"
      >
        {pending ? "Salvando..." : "Cadastrar"}
      </button>
    </form>
  );
}

const inputClass = "mt-1 w-full rounded-md border border-line bg-white px-3 py-2";

function Field({ label, error, children }: { label: string; error?: string; children: ReactNode }) {
  return (
    <label className="block text-sm">
      {label}
      {children}
      {error ? <span className="mt-1 block text-danger">{error}</span> : null}
    </label>
  );
}
