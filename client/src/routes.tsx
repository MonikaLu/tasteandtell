import { createBrowserRouter, RouterProvider } from "react-router";
import App from "./App";
import { Recipe } from "./recipes/Recipe";

export const router = createBrowserRouter([
  {
    path: "/",
    Component: App,
  },
  {
    path: "/recipes",
    Component: Recipe,
  },
]);

const RoutedApp = () => {
  return <RouterProvider router={router} />;
};

export { RoutedApp };
