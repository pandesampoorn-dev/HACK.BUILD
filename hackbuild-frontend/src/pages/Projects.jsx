import { useEffect, useState } from "react";
import api from "../services/api";
import "bootstrap/dist/css/bootstrap.min.css";
import "./Projects.css";

export default function Projects() {
  const [projects, setProjects] = useState([]);
  const [selectedProject, setSelectedProject] = useState(null);

  // ⭐ Get logged in user
  const currentUserId = Number(localStorage.getItem("userId"));
  const currentUserName = localStorage.getItem("name");

  useEffect(() => {
    fetchProjects();
  }, []);

  const fetchProjects = async () => {
    try {
      const res = await api.get("/projects");
      setProjects(res.data);
    } catch (err) {
      console.error("Error fetching projects:", err);
    }
  };

  const joinProject = async (projectId) => {
    try {
      await api.post(`/projects/${projectId}/join/${currentUserId}`);
      fetchProjects();
    } catch (err) {
      console.error("Join failed:", err);
      alert("❌ Could not join. Team might be full or already joined.");
    }
  };

  const leaveProject = async (projectId) => {
    try {
      await api.post(`/projects/${projectId}/leave/${currentUserId}`);
      fetchProjects();
    } catch (err) {
      console.error("Leave failed:", err);
    }
  };

  return (
    <div className="projects-container">
      <h1 className="projects-title">🚀 Explore Projects</h1>
      <p className="projects-subtext">Join a team or build something awesome.</p>

      <div className="projects-grid">
        {projects.map((project) => {
          
          // 🟢 UPDATED — Compare IDs, not names
          const isOwner = currentUserId === project.ownerId;
          const alreadyJoined = project.participants?.some(
            (p) => p.id === currentUserId
          );

          const maxedOut = project.participants?.length >= project.maxTeamSize;

          return (
            <div className="project-card" key={project.id}>
              <div className="card-header">
                <h3>{project.title}</h3>
                ⭐
              </div>

              <p className="desc">{project.description}</p>

              <div className="tags">
                {project.skills && project.skills.length > 0 ? (
                  project.skills.map((skill, i) => (
                    <span className="tag" key={i}>
                      {skill.charAt(0).toUpperCase() + skill.slice(1)}
                    </span>
                  ))
                ) : (
                  <span className="tag empty-tag">No Skills</span>
                )}
              </div>

              <div className="footer-row">
                <p className="members">
                  👥 {project.participants.length}/{project.maxTeamSize} members
                </p>

                {isOwner ? (
                  <button className="owner-btn" disabled>Owner</button>
                ) : alreadyJoined ? (
                  <button className="leave-btn" onClick={() => leaveProject(project.id)}>Leave</button>
                ) : maxedOut ? (
                  <button className="disabled-btn" disabled>Full</button>
                ) : (
                  <button className="action-btn" onClick={() => joinProject(project.id)}>Join</button>
                )}
              </div>

              <button className="details-btn" onClick={() => setSelectedProject(project)}>View Details</button>
            </div>
          );
        })}
      </div>

      {/* 🟣 Modal */}
      {selectedProject && (
        <div className="modal-backdrop" onClick={() => setSelectedProject(null)}>
          <div className="modal-content" onClick={(e) => e.stopPropagation()}>
            <h2>{selectedProject.title}</h2>
            <p>{selectedProject.description}</p>

            <h4>Team Members</h4>

            {/* 🟢 Updated to show name + email + owner badge */}
            {selectedProject.participants.map((m, i) => (
              <p key={i}>
                👤 {m.name} — <small>{m.email}</small>
                {m.id === selectedProject.ownerId ? " 👑 Owner" : ""}
              </p>
            ))}

            <button className="close-btn" onClick={() => setSelectedProject(null)}>Close</button>
          </div>
        </div>
      )}
    </div>
  );
}
