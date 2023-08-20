(ns carpooling.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [carpooling.routes.auth :refer [auth-routes]]
            [carpooling.routes.rides :refer [rides-routes]]
            [carpooling.routes.reservations :refer [reservations-routes]]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [ring.middleware.cors :refer [wrap-cors]]))

(defroutes static-routes
           (route/resources "/"))

(def app-routes
  (routes
    auth-routes
    rides-routes
    reservations-routes
    static-routes))

(def app
  (wrap-cors
  (wrap-defaults app-routes api-defaults)
  :access-control-allow-origin [#"http://localhost:3000"]
  :access-control-allow-methods [:get :put :post :delete :options]
  :access-control-allow-headers ["Origin" "X-Requested-With"
                                 "Content-Type" "Accept"]))