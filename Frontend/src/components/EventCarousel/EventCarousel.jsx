import React from "react";
import data from "./data";
import { useNavigate } from "react-router-dom";
import { motion } from "framer-motion";

function EventCarousel() {
  const navigate = useNavigate();

  return (
    <div className="px-10 mb-20 md:px-28">
      {/* Heading text for the setion */}
      <div className="flex flex-col items-center justify-between mb-10 lg:flex-row max-lg:text-center max-lg:justify-center">
        <motion.div
          initial={{ opacity: 0, x: -50 }}
          whileInView={{ opacity: 1, x: 0 }}
          transition={{ duration: 0.8 }}
          viewport={{ once: true, amount: 0.4 }}
          className="flex flex-col gap-1"
        >
          <p className="text-xl font-medium md:text-2xl">Checkout Events</p>
          <h3 className="text-5xl font-medium max-lg:mb-3 md:text-6xl font-quantico text-primary">
            Top Upcoming Events
          </h3>
        </motion.div>

        <motion.p
          initial={{ opacity: 0, x: 50 }}
          whileInView={{ opacity: 1, x: 0 }}
          transition={{ duration: 0.8 }}
          viewport={{ once: true, amount: 0.4 }}
          className="font-medium md:text-xl"
        >
          Witness amazing and exclusive events like never before.{" "}
          <br className="max-lg:hidden" /> Get your tickets now and be part of
          the experience.
        </motion.p>
      </div>

      {/* Carousel for events available now */}
      <div className="flex flex-col items-center gap-5 sm:grid sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 2xl:grid-cols-5">
        {data.map((event) => (
          <motion.div
            onClick={() => navigate("/")}
            key={event.id}
            className="relative group overflow-hidden transition-all duration-700 border-[5px] border-transparent rounded-md hover:border-primary"
            initial={{ opacity: 0 }}
            whileInView={{ opacity: 1 }}
            viewport={{ once: true, amount: 0.4 }}
            transition={{ duration: 1.5 }}
          >
            <div className="overflow-hidden">
              <img
                src={event.image}
                alt={event.name}
                loading="lazy"
                className="object-cover w-full h-[28rem] transition-transform duration-700 group-hover:scale-105"
              />
            </div>
            <div className="absolute bottom-0 left-0 flex flex-col w-full gap-1 p-5 ">
              <h3 className="text-2xl font-semibold text-frost">
                {event.name}
              </h3>
              <p className="text-sm mb-1.5 text-frost">
                {event.eventType} / {event.eventDate}
              </p>
              <button className="text-frost px-3 py-1.5 border border-frost rounded-md cursor-pointer transition-all duration-700 group-hover:bg-primary group-hover:text-white group-hover:border-primary">
                Get Ticket
              </button>
            </div>
          </motion.div>
        ))}
      </div>
    </div>
  );
}

export default EventCarousel;
