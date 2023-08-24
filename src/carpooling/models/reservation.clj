(ns carpooling.models.reservation
  (:require [clojure.java.jdbc :as sql]
            [carpooling.db :refer :all]))

(defn create-reservation [reservation]
  (sql/insert! db :reservation reservation))

(defn get-users-reservations [user-id]
  (sql/query db
             ["SELECT * FROM reservation r JOIN ride ri ON r.ride_id = ri.ride_id WHERE r.user_id=cast(? as integer)" user-id]))

(defn delete-reservation [reservation-id]
  (sql/delete! db :reservation ["reservation_id=cast(? as integer)" reservation-id]))