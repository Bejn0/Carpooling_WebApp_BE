(ns carpooling.models.ride
  (:require [clojure.java.jdbc :as sql]
            [carpooling.db :refer :all]))

(defn get-rides []
  (sql/query db
             ["SELECT * FROM ride r JOIN person p ON r.user_id=p.user_id"]))
(defn get-users-rides [user-id]
  (sql/query db
             ["SELECT * FROM ride r JOIN person p ON r.user_id=p.user_id WHERE r.user_id=cast(? as integer)" user-id]))

(defn update-ride-space [ride-id space]
  (sql/update! db :ride {:space (Integer/parseInt space)} ["ride_id=cast(? as integer)" ride-id]))

(defn create-ride [ride]
  (println ride)
  (sql/insert! db :ride ride))

(defn delete-ride [ride-id]
  (sql/delete! db :ride ["ride_id=cast(? as integer)" ride-id]))