import React, { useEffect, useState } from "react";
import {
  Statistic,
  Icon,
  Grid,
  Container,
  Image,
  Segment,
  Dimmer,
  Loader,
} from "semantic-ui-react";
import { orderApi } from "../misc/OrderApi";
import { handleLogError } from "../misc/Helpers";
import { useAuth } from "../context/AuthContext";
import { Link } from "react-router-dom";

function Home() {
  const [numberOfUsers, setNumberOfUsers] = useState(0);
  const [numberOfOrders, setNumberOfOrders] = useState(0);
  const [isLoading, setIsLoading] = useState(true);
  const { getUser, userIsAuthenticated, userLogout } = useAuth();
  const user = getUser();

  useEffect(() => {
    async function fetchData() {
      try {
        const responseUsers = await orderApi.numberOfUsers();
        const numberOfUsers = responseUsers.data;

        const responseOrders = await orderApi.numberOfOrders();
        const numberOfOrders = responseOrders.data;

        setNumberOfUsers(numberOfUsers);
        setNumberOfOrders(numberOfOrders);
      } catch (error) {
        handleLogError(error);
      } finally {
        setIsLoading(false);
      }
    }

    fetchData();
  }, []);

  if (isLoading) {
    return (
      <Segment basic style={{ marginTop: window.innerHeight / 2 }}>
        <Dimmer active inverted>
          <Loader inverted size="huge">
            Loading
          </Loader>
        </Dimmer>
      </Segment>
    );
  }

  return (
    <div class="relative bg-cover bg-center h-screen">
      <video
        src="https://www.shutterstock.com/shutterstock/videos/1063773010/preview/stock-footage-medical-development-laboratory-portrait-of-beautiful-caucasian-female-scientist-looking-under.webm"
        class="absolute inset-0 w-full h-full object-cover"
        autoplay="true"
        loop
      />

      <blockquote class="text-2xl font-semibold italic text-center text-slate-900">
        When you look all the time, people think that you're busy.
      </blockquote>
      <div class="absolute inset-0 flex justify-center items-center">
        <div class="bg-white bg-opacity-80 p-8 rounded-lg shadow-lg max-w-md">
          <h2 class="text-2xl font-bold mb-4">
            {`Book your Lab test   `}
            <span class="before:block before:absolute before:-inset-1 before:-skew-y-3 before:bg-pink-500 relative inline-block">
              <span class="relative text-white"> Rigt now</span>
            </span>
          </h2>
          <p class="mb-4">ABC lab set Your Appoiment allredy</p>
          <Link
            to="/appoimentpage"
            class="inline-block bg-blue-500 text-white py-2 px-4 rounded"
          >
            Book Appoiment Here{" "}
          </Link>
        </div>
      </div>
    </div>
  );
}

export default Home;
