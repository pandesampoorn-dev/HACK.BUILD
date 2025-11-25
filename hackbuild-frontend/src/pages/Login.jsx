import { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../services/api";
import "../styles/auth.css";

export default function Login() {
  const [email, setEmail] = useState("");
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const res = await api.post("/auth/login", { email });

      localStorage.setItem("userId", res.data.id);
      localStorage.setItem("name", res.data.name);

      alert("✔ Logged in successfully");
      navigate("/home");
    } catch (err) {
      alert("❌ Invalid email! User not found.");
    }
  };

  return (
    <div className="auth-page">

      {/* Logo */}
      <h1 className="auth-logo">HACK<span>.BUILD</span></h1>
      <p className="auth-sub">Find your team. Build the future.</p>

      {/* Card */}
      <div className="auth-card">
        <h2 className="auth-title">Welcome Back 👋</h2>

        <form onSubmit={handleLogin} className="auth-form">

          <label>Email</label>
          <input
            type="email"
            placeholder="you@company.com"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
            className="auth-input"
          />

          <button type="submit" className="auth-btn">
            Login
          </button>
        </form>

        <button className="auth-switch" onClick={() => navigate("/signup")}>
          Create Account →
        </button>
      </div>
    </div>
  );
}
