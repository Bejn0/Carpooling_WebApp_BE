(ns carpooling.routes.rides
  (:require [clojure.data.json :as json]
            [compojure.core :refer :all]
            [ring.util.response :as response]
            [carpooling.models.ride :as db]))

(defn get-rides []
           (response/response (db/get-rides)))

(defroutes rides-routes
           (GET "/rides" []
             (json/write-str
               (get-rides))
             ))
