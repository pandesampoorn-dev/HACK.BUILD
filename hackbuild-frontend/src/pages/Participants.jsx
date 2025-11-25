import { useEffect, useState } from "react";
import api from "../services/api";
import "bootstrap/dist/css/bootstrap.min.css";
import "./Participants.css";

export default function Participants() {
  const [users, setUsers] = useState([]);

  useEffect(() => {
    fetchUsers();
  }, []);

  const fetchUsers = async () => {
    try {
      const res = await api.get("/users");
      setUsers(res.data);
    } catch (err) {
      console.error("Error fetching users:", err);
    }
  };

  const getInitials = (name) => {
    if (!name) return "";
    return name
      .split(" ")
      .map((w) => w[0])
      .join("")
      .toUpperCase();
  };

  return (
    <div className="participants-container">
      <h1 className="participants-title">👾 Meet The Hackers</h1>
      <p className="participants-subtext">
        Connect with developers who joined Hack.Build 🚀
      </p>

      <div className="participants-grid">
        {users.map((user) => (
          <div className="participant-card" key={user.id}>

            {/* Avatar */}
            <div className="avatar">{getInitials(user.name)}</div>

            {/* User Details */}
            <h3 className="name">{user.name}</h3>
            <p className="email">📩 {user.email}</p>

            {/* Projects */}
            <div className="projects-section">
              <h5 className="project-title">Joined Projects:</h5>

              {user.projects && user.projects.length > 0 ? (
                <div className="project-list">
                  {user.projects.map((project, i) => (
                    <span key={i} className="project-badge">
                      {project}
                    </span>
                  ))}
                </div>
              ) : (
                <p className="no-projects">❌ Not part of any project yet</p>
              )}
            </div>

          </div>
        ))}
      </div>
    </div>
  );
}
