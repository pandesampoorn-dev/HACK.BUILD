import { Link, useNavigate } from "react-router-dom";
import "./Navbar.css";

export default function Navbar() {
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem("user");
    navigate("/login");
  };

  return (
    <nav className="navbar">
      <div className="nav-left">
        <span className="logo">HACK<span className="yellow">.BUILD</span></span>
      </div>

      <div className="nav-links">
        <Link to="/">Home</Link>
        <Link to="/participants">Participants</Link>
        <Link to="/projects">Projects</Link>
        <Link className="create-btn" to="/create">+ Create</Link>
      </div>

      <div className="nav-right">
        <button className="logout-btn" onClick={handleLogout}>Logout</button>
      </div>
    </nav>
  );
}
