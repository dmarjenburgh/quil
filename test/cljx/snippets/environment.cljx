(ns snippets.environment
  (:require #+cljs quil.snippet
            #+clj [quil.snippet :refer [defsnippet]]
            [quil.core :as q :include-macros true])
  #+cljs
  (:use-macros [quil.snippet :only [defsnippet]]))

(defsnippet current-frame-rate-target-frame-rate {}
  (q/background 255)
  (q/fill 0)
  (q/text (str (q/current-frame-rate)) 10 20)
  (q/text (str (q/target-frame-rate)) 10 40))

(defsnippet current-graphics {}
  (q/background 255)
  (q/fill 0 0 255)
  (.rect (q/current-graphics) 0 0 100 100)

  (let [gr (q/create-graphics 100 100)]
    (q/with-graphics gr
      (.fill (q/current-graphics) 255 255 0)
      (.ellipse (q/current-graphics) 50 50 100 100))
    (q/image gr 70 70)))

(defsnippet cursor {}
  (q/no-cursor)
  (q/cursor)

  (doseq [type [:arrow :cross :hand :move :text :wait]]
    (q/cursor type)))

#+clj
(defsnippet cursor-image {}
  (let [curs (q/create-graphics 32 32)]
    (q/with-graphics curs
      (q/fill 0 0)
      (q/ellipse 16 16 32 32)
      (q/ellipse 16 16 8 8))
    (q/cursor-image curs)
    (q/cursor-image curs 16 16)))

#+cljs
(defsnippet cursor-image
  {:setup (q/set-state! :image (q/request-image "texture.jpg"))}
  (if (zero? (.-width (q/state :image)))
    (q/text "Loading" 10 10)
    (do
      (q/cursor-image (q/state :image) 16 16)
      (q/image (q/state :image) 0 0))))

(defsnippet focused {}
  (q/background 255)
  (q/fill 0)
  (q/text (str (q/focused)) 10 20))

(defsnippet frame-count {}
  (q/background 255)
  (q/fill 0)
  (q/text (str (q/frame-count)) 10 20))

(defsnippet frame-rate {}
  (q/background 255)
  (q/fill 0)
  (q/text (str (q/target-frame-rate)) 10 20)

  (q/frame-rate (inc (rand-int 5))))

(defsnippet height-width {}
  (q/background 255)
  (q/fill 0)
  (q/text (str (q/width) "x" (q/height)) 10 20))

(defsnippet no-cursos {}
  (q/no-cursor))

#+clj
(defsnippet screen-height-screen-width {}
  (q/background 255)
  (q/fill 0)
  (let [w (q/screen-width)
        h (q/screen-height)]
    (q/text (str w "x" h) 10 20)))
