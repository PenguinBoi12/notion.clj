(ns notion.types.user)

(defrecord User [id name email avatar bot?])

(defn build-user [m]
  (let [bot? (= (:type m) "bot")]
    (->User (:id m) (:name m) (:email (:person m)) (:avatar_url m) bot?)))
