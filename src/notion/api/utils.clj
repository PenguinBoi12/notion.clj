(ns notion.api.utils
  (:require
    [notion.api.core :refer [send-request]]
    [slingshot.slingshot :refer [throw+]]))

(defonce ^:private model-routes {:user "/users/"
                                 :page "/pages/"
                                 :database "/databases/"
                                 :block "/blocks/"})

(defn- get-route
  "Returns the path of the given model."
  ([model-keyword]
    (if-not (contains? model-routes model-keyword)
      (throw+ {:type :invalid-key
               :key model-keyword
               :hint "Only the follwing keys are valid :user, :page, :database and :block"}))
    (get model-routes model-keyword))
  ([model-keyword id]
    (str (get-route model-keyword) id)))

(defn fetch
  "Fetch the given model. If an 'id' is given, fetches only one record.
   Otherwise, everthing is fetched."
  ([client model-keyword]
    (:results (send-request :get (get-route model-keyword) client)))
  ([client model-keyword id]
    (let [path (get-route model-keyword id)]
      (send-request :get path client))))

; TODO Check if create! and update! are really working and if the params cannot
; be made better
(defn create!
  "Creates a new object."
  ([client model-keyword object]
    (let [params (dissoc object :id)
          path (get-route model-keyword)]
      (send-request :put path client params))))

(defn update!
  "Updates the give object"
  ([client model-keyword object]
    (let [params (dissoc object :id)
          path (get-route model-keyword)]
      (send-request :put path client params))))

(defn delete!
  "Permanently deletes the given model with the given id"
  [client model-keyword id]
  (let [path (get-route model-keyword id)]
    (send-request :delete path client)))

(defn search
  "Searches pages and databases (including childrens) titles that matches
   the given query.

   params is a map that can includes those keys:
    :sort - Sorts the result by the provided criteria. Only one at the time
            is currently allowed.
      :direction - Direction to sort. Must be :ascending or :descending
      :timestamp - Name of the timestamp to sort. Possible value is :last_edited_time

    :filter - Filters the results based on the provided criteria. filter must
              be a map that include :value and/or :property
      :value - Value of the property. Can be :database or :page. At the moment,
               only :object is available (see notion's doc)
      :property - Name of the property. Can be :database or :page. At the moment
                  only :object is available (see notion's doc)
    :start_cursor - Pagination starting point or the result.
    :page_size - Number or result per page. Maximum is 100.

    https://developers.notion.com/reference/post-search"
  ([client query params]
    (let [params (merge {:query query} params)]
      (send-request :post "/search" client params))))
