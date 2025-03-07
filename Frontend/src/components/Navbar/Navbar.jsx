import React, { useContext, useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { AppContext } from "../../context/AppContext";
import axios from "axios";

function Navbar() {
  const { user, setUser } = useContext(AppContext);
  const [isMenuOpen, setIsMenuOpen] = useState(false);
  const [isScrollingUp, setIsScrollingUp] = useState(true);
  const [lastScrollY, setLastScrollY] = useState(0);


  const logout = async (e) => {
    e.preventDefault();
  
    try {
      const response = await axios.get("http://localhost:8080/api/auth/logout", {
        headers: { "Content-Type": "application/json" },
        withCredentials: true,
      });
  
      if (response.status === 200) {
        alert("Successful Logout");
        setUser(null);  // ✅ Clear the user state
        window.location.href = "/signin"; // ✅ Redirect to Sign-in Page
      }
    } catch (error) {
      alert("Logout Failed!!!");
      console.error("Logout Error:", error);
    }
  };
  

  const menuToggler = () => {
    setIsMenuOpen(!isMenuOpen);
  };

  // Function to detect scroll and modify navbar visibility
  useEffect(() => {
    const handleScroll = () => {
      if (window.scrollY < lastScrollY) {
        setIsScrollingUp(true); // Scrolling up -> Show navbar
      } else if (window.scrollY > lastScrollY) {
        setIsScrollingUp(false); // Scrolling down -> Hide navbar
      }
      setLastScrollY(window.scrollY);
    };

    window.addEventListener("scroll", handleScroll);
    return () => {
      window.removeEventListener("scroll", handleScroll);
    };
  }, [lastScrollY]);

  return (
    <>
      {/* ========= Background Blur ============= */}
      <div
        className={`fixed inset-0 bg-black/50 transition-opacity duration-500 ${
          isMenuOpen ? "visible opacity-100 blur-sm" : "invisible opacity-0"
        }`}
        onClick={menuToggler}
      ></div>

      {/* ========= Desktop Menu ============= */}
      <nav
        className={`fixed top-0 left-0 z-10 flex items-center justify-between w-full px-10 py-5 bg-black/60 border-b-[1px] border-frost lg:px-28 backdrop-blur-xs transition-transform duration-300 ease-in-out ${
          isScrollingUp ? "translate-y-0" : "-translate-y-full"
        }`}
      >
        <Link to={"/"}>
          <h1 className="text-5xl font-semibold cursor-pointer font-acme text-frost">
            Tickety
          </h1>
        </Link>

        <menu className="hidden gap-10 text-xl font-medium text-frost lg:flex">
          <Link
            to={"/"}
            className="font-semibold transition-all duration-700 decoration-primary hover:text-primary hover:underline underline-offset-4"
          >
            <li>Home</li>
          </Link>

          <Link
            to={"/movie"}
            className="font-semibold transition-all duration-700 decoration-primary hover:text-primary hover:underline underline-offset-4"
          >
            <li>Movies</li>
          </Link>

          <Link
            to={"/event"}
            className="font-semibold transition-all duration-700 decoration-primary hover:text-primary hover:underline underline-offset-4"
          >
            <li>Events</li>
          </Link>

          <Link
            to={"/about"}
            className="font-semibold transition-all duration-700 decoration-primary hover:text-primary hover:underline underline-offset-4"
          >
            <li>About Us</li>
          </Link>

          <Link
            to={"/contact"}
            className="font-semibold transition-all duration-700 decoration-primary hover:text-primary hover:underline underline-offset-4"
          >
            <li>Contact Us</li>
          </Link>

          {!user ? (
            <Link
              to={"/signin"}
              className="font-semibold transition-all duration-700 decoration-primary hover:text-primary hover:underline underline-offset-4"
            >
              <li>Join Now</li>
            </Link>
          ) : (
            
            <><Link
                to={"/profile"}
                className="font-semibold transition-all duration-700 decoration-primary hover:text-primary hover:underline underline-offset-4"
              >
                <li>Profile</li>
                {/* <i className="text-xl rounded-[25%] border p-2.5 ri-user-3-fill"></i> */}
              </Link><Link
              onClick={logout}
                className="font-semibold transition-all duration-700 decoration-primary hover:text-primary hover:underline underline-offset-4"
              >
                <li>Logout</li>
                </Link></>
            
  
            
          )}
          
        </menu>

        {/* ========= Menu Togglers ============= */}

        <div className="lg:hidden">
          <i
            onClick={menuToggler}
            className={`text-4xl text-frost font-medium transition-transform duration-300 ease-in-out ${
              isMenuOpen ? "rotate-180 text-primary" : "rotate-0"
            } ri-${isMenuOpen ? "close-line" : "menu-5-line"}`}
          ></i>
        </div>
      </nav>

      {/* ========= Mobile Menu ============= */}
      <menu
        className={`fixed top-0 left-0 z-20 flex flex-col px-10 py-16 space-y-2 text-2xl h-screen max-sm:w-[75%] md:w-[40%] gap-5 lg:hidden bg-dark text-frost  transition-transform duration-500 ease-in-out ${
          isMenuOpen ? "translate-x-0" : "-translate-x-full"
        }`}
      >
        <Link
          to={"/"}
          className="transition-all duration-700 decoration-primary hover:text-primary hover:underline underline-offset-4"
        >
          <li>Home</li>
        </Link>
        <Link
          to={"/movie"}
          className="transition-all duration-700 decoration-primary hover:text-primary hover:underline underline-offset-4"
        >
          <li>Movies</li>
        </Link>
        <Link
          to={"/event"}
          className="transition-all duration-700 decoration-primary hover:text-primary hover:underline underline-offset-4"
        >
          <li>Events</li>
        </Link>
        <Link
          to={"/contact"}
          className="transition-all duration-700 decoration-primary hover:text-primary hover:underline underline-offset-4"
        >
          <li>Contact Us</li>
        </Link>
        <Link
          to={"/about"}
          className="transition-all duration-700 decoration-primary hover:text-primary hover:underline underline-offset-4"
        >
          <li>About Us</li>
        </Link>
        {!user ? (
          <Link
            to={"/signin"}
            className="transition-all duration-700 decoration-primary hover:text-primary hover:underline underline-offset-4"
          >
            <li>Join Now</li>
          </Link>
        ) : (
          <Link
            to={"/profile"}
            className="transition-all duration-700 decoration-primary hover:text-primary hover:underline underline-offset-4"
          >
            <li>Profile</li>
          </Link>
        )}
      </menu>
    </>
  );
}

export default Navbar;
