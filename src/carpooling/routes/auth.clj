(ns carpooling.routes.auth
  (:require [clojure.data.json :as json]
            [compojure.core :refer :all]
            [ring.util.response :as response]
            [carpooling.crypt :as crypt]
            [carpooling.models.user :as db]
            [struct.core :as st]))

(def auth-register-scheme
  {:first-name [st/required st/string]
   :last-name [st/required st/string]
   :email [st/required st/email]
   :password [st/required [st/min-count 6]]
   :password-confirmation [st/required [st/identical-to :password]]})

(defn validate-user [first-name last-name email password password-confirmation]
  (st/validate {:first-name first-name
                :last-name last-name
                :email email
                :password password
                :password-confirmation password-confirmation} auth-register-scheme))

(defn user-to-session [user]
                              {:id (:user_id user)
                                      :name (:first_name user)
                                      :email (:email user)})

(defn handle-login [email password]
  (let [user (db/get-user-by-email email)]
    (if (and user (crypt/verify password (:password user)))
      (response/response (user-to-session user)))))

(defn handle-registration [first-name last-name email password password-confirmation]
  (let [errors (first (validate-user first-name last-name email password password-confirmation))]
    (if errors
      (println "Fields are not valid.")
      (if (db/get-user-by-email email)
        (println "User with the same email already exists")
        (do
          (db/create-user {:first_name first-name :last_name last-name :email email :password (crypt/encrypt password)})
          (let [user (db/get-user-by-email email)]
            (response/response (user-to-session user))))))))


(defroutes auth-routes
           (POST "/register" request
             (json/write-str(let [{:keys [first_name last_name email password password_confirmation]} (json/read-str (slurp(:body request)) :key-fn keyword)]
                       (handle-registration first_name last_name email password password_confirmation)
                       ))
             )
           (POST "/login" request
             (json/write-str(let [{:keys [email password]} (json/read-str (slurp(:body request)) :key-fn keyword)]
                              (handle-login email password)
                              ))))