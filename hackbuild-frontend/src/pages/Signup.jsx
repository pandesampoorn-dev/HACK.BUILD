import { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../services/api";
import "../styles/auth.css";

export default function Signup() {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const navigate = useNavigate();

  const handleSignup = async (e) => {
    e.preventDefault();
    try {
      const res = await api.post("/auth/signup", { name, email });

      // Save user
      localStorage.setItem("userId", res.data.id);
      localStorage.setItem("name", res.data.name);

      alert("🎉 Account created successfully!");
      navigate("/login"); // redirect after signup
    } catch (err) {
      console.error(err);
      alert("❌ Signup failed. Email may already exist.");
    }
  };

  return (
    <div className="auth-page">

      {/* Logo */}
      <h1 className="auth-logo">
        HACK<span>.BUILD</span>
      </h1>
      <p className="auth-sub">Find your team. Build the future.</p>

      {/* Card */}
      <div className="auth-card">
        <h2 className="auth-title">Create Account ✨</h2>

        <form onSubmit={handleSignup} className="auth-form">

          <label>Name</label>
          <input
            type="text"
            className="auth-input"
            placeholder="John Carter"
            value={name}
            onChange={(e) => setName(e.target.value)}
            required
          />

          <label>Email</label>
          <input
            type="email"
            className="auth-input"
            placeholder="you@company.com"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />

          <button type="submit" className="auth-btn">
            Sign Up
          </button>
        </form>

        <button className="auth-switch" onClick={() => navigate("/login")}>
          Already have an account? →
        </button>
      </div>
    </div>
  );
}
