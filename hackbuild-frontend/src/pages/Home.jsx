import { useNavigate } from "react-router-dom";
import "./Home.css";

export default function Home() {
  const navigate = useNavigate();

  return (
    <div className="home-container">
      <div className="home-content">
        
        <h1 className="home-title">
          HACK<span className="highlight">.BUILD</span>
        </h1>

        <h2 className="welcome-text">Welcome to <span className="highlight">HACK.BUILD</span></h2>

        <p className="home-description">
          Systems online. Creators logged in. Innovation loading...
          <br /><br />
          This isn’t just another platform — it’s where hackers, builders,
          and future founders assemble.
          <br /><br />
          Browse ideas. Join squads. Deploy your skills.
          <br/>
          Because the next breakthrough won’t build itself.
        </p>

        <p className="cta">
          ⚡ Find your team. Build the future. ⚡
        </p>

        <div className="home-buttons">
          <button className="yellow-btn" onClick={() => navigate("/projects")}>
            View Projects ⚡
          </button>

          <button className="dark-btn" onClick={() => navigate("/participants")}>
            Meet Participants 👥
          </button>
        </div>

      </div>
    </div>
  );
}
