(ns carpooling.routes.reservations
  (:require [clojure.data.json :as json]
            [compojure.core :refer :all]
            [ring.util.response :as response]
            [carpooling.models.reservation :as db]
            [carpooling.models.ride :as ride-db]))

(defn create-reservation
  [ride-id user-id space space-left]
  (ride-db/update-ride-space ride-id space-left)
  (db/create-reservation {:ride_id (Integer/parseInt ride-id) :user_id (Integer/parseInt user-id) :space (Integer/parseInt space)})
  (response/status 200))

(defn delete-reservation [reservation-id ride-id space]
  (ride-db/update-ride-space ride-id space)
  (response/response (db/delete-reservation reservation-id)))

(defroutes reservations-routes
           (GET "/reservations/:id" request
             (let [id (:id (:route-params request))]
               (json/write-str
                 (response/response (db/get-users-reservations id)))
               ))
           (POST "/reservations" request
             (json/write-str(let [{:keys [ride_id user_id space space_left]} (json/read-str (slurp(:body request)) :key-fn keyword)]
                              (create-reservation ride_id user_id space space_left)
                              ))
             )
           (POST "/reservations/:id" request
             (let [id (:id (:route-params request))]
               (json/write-str(let [{:keys [ride_id space]} (json/read-str (slurp(:body request)) :key-fn keyword)]
                                (delete-reservation id ride_id space)
                                ))
               )
             ))