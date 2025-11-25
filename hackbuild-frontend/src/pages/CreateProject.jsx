import { useState } from "react";
import api from "../services/api";
import "./CreateProject.css";
import { useNavigate } from "react-router-dom";

export default function CreateProject() {
  const navigate = useNavigate();

  // form states
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [skills, setSkills] = useState("");
  const [teamSize, setTeamSize] = useState(4); // ✅ default value fixed

  // temporary user until authentication system is connected
  const ownerId = 2; // later replace with localStorage.getItem("userId")

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      await api.post("/projects", {
        title,
        description,
        ownerId,
        skills: skills.split(",").map((s) => s.trim()),
        maxTeamSize: Number(teamSize),  // ✅ FIX: ensure number
      });

      alert("🚀 Project added successfully!");
      navigate("/projects");
    } catch (err) {
      console.error(err);
      alert("❌ Failed to create project!");
    }
  };

  return (
    <div className="create-wrapper">
      <h1 className="create-title">Create a New Project</h1>

      <form className="create-form" onSubmit={handleSubmit}>

        <label>Project Title</label>
        <input
          type="text"
          placeholder="e.g., AI-Powered Bug Finder"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          required
        />

        <label>Description</label>
        <textarea
          placeholder="What is your project about?"
          value={description}
          onChange={(e) => setDescription(e.target.value)}
          required
        />

        <div className="form-row">
          <div>
            <label>Skills Needed (comma separated)</label>
            <input
              type="text"
              placeholder="e.g., React, AI, Spring Boot"
              value={skills}
              onChange={(e) => setSkills(e.target.value)}
            />
          </div>

          <div>
            <label>Max Team Size</label>
            <input
              type="number"
              min="2"
              max="10"
              value={teamSize}
              onChange={(e) => setTeamSize(Number(e.target.value))}  // ✅ FIXED
            />
          </div>
        </div>

        <button type="submit" className="create-btn">
          Add Project ⚡
        </button>
      </form>
    </div>
  );
}
