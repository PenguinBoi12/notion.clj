(ns notion.types.page)

(defrecord Page [id created-time created-by-id last-edited-time last-edited-by-id
                 archived? icon cover properties parent-id url])

(defn make-page [id created-time created-by-id last-edited-time last-edited-by-id
                 archived? icon cover properties parent-id url]
  (->Page id created-time created-by-id last-edited-time last-edited-by-id
          archived? icon cover properties parent-id url))

(defn build-page [m]
  m)
