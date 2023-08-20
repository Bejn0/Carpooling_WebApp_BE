(ns carpooling.routes.reservations
  (:require [clojure.data.json :as json]
            [compojure.core :refer :all]
            [ring.util.response :as response]
            [carpooling.models.reservation :as db]))

(defn create-reservation
  []
  (db/create-reservation {})
  (response/status 200))

(defroutes reservations-routes
           (POST "/reservations" []
             (json/write-str
               (create-reservation))
             ))