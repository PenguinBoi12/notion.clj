(ns notion.types.user)

(defrecord User [id name email avatar bot?])

(def bot? (partial = "bot"))

(defn build-user [m]
  (let [bot? (bot? (:type m))]
    (->User (:id m) (:name m) (:email (:person m)) (:avatar_url m) bot?)))
