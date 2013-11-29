; Authors: Brody Berson, Bill Koppelberger
; Tic-Tac-Toe Project
; CIS343 - W13

(ns tictactoe.core
  (:gen-class))

(def board [1 2 3 4 5 6 7 8 9])

(defn displayBoard [board]
        (println (nth board 0) "|" (nth board 1) "|" (nth board 2))
        (println "--+---+---")
        (println (nth board 3) "|" (nth board 4) "|" (nth board 5))
        (println "--+---+---")
        (println (nth board 6) "|" (nth board 7) "|" (nth board 8)))

(defn draw? [ board ]
	(= 2 (count (set board))))

(defn winner? [board]
	  (or
		; rows all match?
		(= (nth board 0), (nth board 1), (nth board 2))
		(= (nth board 3), (nth board 4), (nth board 5))
		(= (nth board 6), (nth board 7), (nth board 8))
		; columns all match?
		(= (nth board 0), (nth board 3), (nth board 6))
		(= (nth board 1), (nth board 4), (nth board 7))
		(= (nth board 2), (nth board 5), (nth board 8))
		; diagonals all match?
		(= (nth board 0), (nth board 4), (nth board 8))
		(= (nth board 2), (nth board 4), (nth board 6))))

(defn valid-move [move board]
        (some #(= % move) board))

(def competitors (cycle ["X" "O"]))

(defn updateBoard [ board i val ]
	(def board (assoc board (dec i) val))(println))


(defn play-game [p]
   	((println "Player " (first p) ", enter a move: ")
   	(let [command(read)]
           	(println "You have entered " command)
           	(def tmp (first p))
            	(def tmp2 (rest p))
            	(if (= (str command) "quit")
            		(System/exit 0))
           	(if (valid-move command board) 
           		(do (updateBoard board command tmp) (displayBoard board) 
           			(if (winner? board) (do (println (first p) " won!") (System/exit 0)))
           			(if (draw? board) (do (println "DRAW!") (System/exit 0)))
           		(play-game(rest p))) 
           	((println "Invalid input!")(play-game (seq p)))))))
    

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  ;; work around dangerous default behaviour in Clojure
  (alter-var-root #'*read-eval* (constantly false))

        (println "Welcome to Tic-Tac-Toe!")
        (println "To leave, type quit or finish the game!.")
        (displayBoard board)
        (play-game competitors))