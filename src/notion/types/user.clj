(ns notion.types.user)

(def bot? (partial = "bot"))

(defrecord User [id name email avatar bot?])

(defn make-user [id name email avatar bot?]
    (->User id name email avatar bot?))

(defn build-user [m]
  (let [bot? (bot? (:type m))]
    (make-user (:id m) (:name m) (:email (:person m)) (:avatar_url m) bot?)))
