(ns carpooling.models.user
  (:require [clojure.java.jdbc :as sql]
            [carpooling.db :refer :all]))

(defn create-user [user]
  (sql/insert! db :person user))

(defn get-user-by-email [email]
  (sql/query db
             ["SELECT * FROM person WHERE email = ?", email]
             {:result-set-fn first}))
