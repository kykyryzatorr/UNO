
import java.io.FileNotFoundException;
import java.util.ArrayList;

import java.util.Random;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Controller {
	public ArrayList<Card> cardsPlayer1;
	public ArrayList<Card> cardsPlayer2;
	public ArrayList<Card> addcards;
	public ArrayList<Card> centercards;
	public ArrayList<Card> cardsPlayer4;
	public ArrayList<ImageView> black;
	public Random rand;

	public Controller() {

		cardsPlayer1 = new ArrayList<Card>();
		cardsPlayer2 = new ArrayList<Card>();
		addcards = new ArrayList<Card>();
		centercards = new ArrayList<Card>();
		black = new ArrayList<ImageView>();
		cardsPlayer4 = new ArrayList<Card>();
		rand = new Random();

	}

	public void onePlayer(BorderPane pane, Pane pane1, Pane pane2, Pane centerPane, Label label, VBox pane3,
			Button[] buttons, Button button, Stage st, Stage primaryStage)
			throws IllegalArgumentException, FileNotFoundException {
		pane1.widthProperty().addListener(e -> {
			int i = 0;
			for (Card image : CardDeck.usedcards) {
				if (i != 0) {

					image.getImage().setX(CardDeck.usedcards.get(i - 1).getImage().getX()
							+ pane1.getWidth() / CardDeck.usedcards.size());
				} else {
					image.getImage().setX(0);
				}
				i++;
			}
		});
		centerPane.widthProperty().addListener(e -> {
			int i = 0;
			for (Card image : centercards) {
				if (i != 0) {

					image.getImage().setX(
							centercards.get(i - 1).getImage().getX() + centerPane.getWidth() / centercards.size());
				} else {
					image.getImage().setX(0);
				}
				i++;
			}
		});
		pane2.widthProperty().addListener(e -> {
			int i = 0;
			for (ImageView image : black) {
				if (i != 0) {

					image.setX(black.get(i - 1).getX() + pane2.getWidth() / black.size());
				} else {
					image.setX(0);
				}
				i++;
			}
		});

		for (int i = 0; i < 7; i++) {
			pane1.getChildren().add(CardDeck.cards.get(i).getImage());

			cardsPlayer1.add(CardDeck.cards.get(i));

			CardDeck.usedcards.add(CardDeck.cards.get(i));
			CardDeck.cards.remove(i);
		}

		for (int i = 0; i < 7; i++) {
			black.add(new ImageView(Card.backImage));
			pane2.getChildren().add(black.get(i));
			cardsPlayer2.add(CardDeck.cards.get(i));
			CardDeck.cards.remove(i);
		}
		for (int i = 0; i < 32; i++) {
			addcards.add(CardDeck.cards.get(i));
			CardDeck.cards.remove(i);
		}

		pane3.getChildren().add(button);
		pane.setBottom(pane1);
		pane.setTop(pane2);
		pane.setCenter(centerPane);
		pane.setLeft(pane3);

		EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
			String state = "reg";
			Card img;

			
			@Override
			public void handle(MouseEvent e) {

				for (Card image : cardsPlayer1) {
					if (e.getTarget().equals(image.getImage())) {
						pane1.getChildren().remove(image.getImage());
						centerPane.getChildren().add(image.getImage());
						centercards.add(image);
						CardDeck.usedcards.remove(image);
						cardX(pane1);

						
						centerX(centerPane);
						img = image;
						pane3.getChildren().remove(label);

						if (image.getId().equals("wild_color_changer") || image.getId().equals("wild_pick_four")) {
							int x = 0;
							for (Card image1 : cardsPlayer2) {
								if (image1.getId().equals("wild_color_changer")) {
									centerPane.getChildren().add(image1.getImage());
									centercards.add(image1);
									centerX(centerPane);

									pane2.getChildren().remove(0);
									black.remove(0);
									backX(pane2);
									cardsPlayer2.remove(image1);

									state = "wildcolor";

									break;
								} else if (image1.getId().equals("wild_pick_four")) {
									centerPane.getChildren().add(image1.getImage());
									centercards.add(image1);
									centerX(centerPane);

									pane2.getChildren().remove(0);
									black.remove(0);
									backX(pane2);
									cardsPlayer2.remove(image1);
									state = "wildpicker";

									break;
								} else if (x >= cardsPlayer2.size() - 1) {

									if (image.getId().equals("wild_pick_four")) {

										state = "pick four";
									} else if (image.getId().equals("wild_color_changer")) {

										state = "change color";

									}
								}
								x++;

							}

						} else if (image.getId().equals("skip") || image.getId().equals("reverse")) {

							state = "reg";
						} else if (image.getId().equals("picker")) {

							state = "picker";
							int x = 0;
							for (Card image2 : cardsPlayer2) {
								if (image2.getId().equals(image.getId())
										|| image2.getColor().equals(image.getColor())) {
									centerPane.getChildren().add(image2.getImage());
									centercards.add(image2);
									centerX(centerPane);

									pane2.getChildren().remove(0);
									black.remove(0);
									backX(pane2);
									cardsPlayer2.remove(image2);
									if (image2.getId().equals("skip") || image2.getId().equals("reverse")) {
										int y = 0;
										for (Card image3 : cardsPlayer2) {
											if (image3.getId().equals(image2.getId())
													|| image3.getColor().equals(image2.getColor())) {
												centerPane.getChildren().add(image3.getImage());
												centercards.add(image3);
												centerX(centerPane);

												pane2.getChildren().remove(0);
												black.remove(0);
												backX(pane2);
												cardsPlayer2.remove(image3);
												if (image3.getId().equals("skip") || image3.getId().equals("reverse")) {
													int z = 0;
													for (Card image4 : cardsPlayer2) {
														if (image4.getId().equals(image3.getId())
																|| image4.getColor().equals(image3.getColor())) {
															centerPane.getChildren().add(image4.getImage());
															centercards.add(image4);
															centerX(centerPane);

															pane2.getChildren().remove(0);
															black.remove(0);
															backX(pane2);
															cardsPlayer2.remove(image4);
															if (image4.getId().equals("skip")
																	|| image4.getId().equals("reverse")) {
																int c = 0;
																for (Card image5 : cardsPlayer2) {
																	if (image5.getId().equals(image4.getId()) || image5
																			.getColor().equals(image4.getColor())) {
																		centerPane.getChildren().add(image5.getImage());
																		centercards.add(image5);
																		centerX(centerPane);

																		pane2.getChildren().remove(0);
																		black.remove(0);
																		backX(pane2);
																		cardsPlayer2.remove(image5);
																		if (image5.getId().equals("skip")
																				|| image5.getId().equals("reverse")) {
																			int v = 0;
																			for (Card image6 : cardsPlayer2) {
																				if (image6.getId()
																						.equals(image5.getId())
																						|| image6.getColor().equals(
																								image5.getColor())) {
																					centerPane.getChildren()
																							.add(image6.getImage());
																					centercards.add(image6);
																					centerX(centerPane);

																					pane2.getChildren().remove(0);
																					black.remove(0);
																					backX(pane2);
																					cardsPlayer2.remove(image6);
																					break;
																				} else if (v >= cardsPlayer2.size()
																						- 1) {
																					black.add(new ImageView(
																							Card.backImage));
																					pane2.getChildren().add(black
																							.get(black.size() - 1));
																					backX(pane2);
																					break;
																				}
																				v++;
																			}

																		} else if (image2.getId().equals("picker")) {
																			state = "addtwo";
																		}
																		break;
																	} else if (c >= cardsPlayer2.size() - 1) {
																		black.add(new ImageView(Card.backImage));
																		pane2.getChildren()
																				.add(black.get(black.size() - 1));
																		backX(pane2);
																		cardsPlayer2.add(CardDeck.cards.get(0));
																		CardDeck.cards.remove(0);
																		break;
																	}
																	c++;
																}

															} else if (image2.getId().equals("picker")) {
																state = "addtwo";
															}
															break;
														} else if (z >= cardsPlayer2.size() - 1) {
															black.add(new ImageView(Card.backImage));
															pane2.getChildren().add(black.get(black.size() - 1));
															backX(pane2);
															cardsPlayer2.add(CardDeck.cards.get(0));
															CardDeck.cards.remove(0);
															break;
														}
														z++;
													}

												} else if (image2.getId().equals("picker")) {
													state = "addtwo";
												}
												break;
											} else if (y >= cardsPlayer2.size() - 1) {
												black.add(new ImageView(Card.backImage));
												pane2.getChildren().add(black.get(black.size() - 1));
												backX(pane2);
												cardsPlayer2.add(CardDeck.cards.get(0));
												CardDeck.cards.remove(0);
												break;
											}
											y++;
										}

									} else if (image2.getId().equals("picker")) {
										state = "addtwo";
									}
									break;
								} else if (x >= cardsPlayer2.size() - 1) {
									black.add(new ImageView(Card.backImage));
									pane2.getChildren().add(black.get(black.size() - 1));
									backX(pane2);
									cardsPlayer2.add(CardDeck.cards.get(0));
									CardDeck.cards.remove(0);
									break;
								}
								x++;

							}
						} else {
							state = "regadd";
							int x = 0;
							for (Card image2 : cardsPlayer2) {

								if (image2.getId().equals(image.getId())
										|| image2.getColor().equals(image.getColor())) {
									centerPane.getChildren().add(image2.getImage());
									centercards.add(image2);
									centerX(centerPane);

									pane2.getChildren().remove(0);
									black.remove(0);
									backX(pane2);
									cardsPlayer2.remove(image2);
									if (image2.getId().equals("skip") || image2.getId().equals("reverse")) {
										int y = 0;
										for (Card image3 : cardsPlayer2) {
											if (image3.getId().equals(image2.getId())
													|| image3.getColor().equals(image2.getColor())) {
												centerPane.getChildren().add(image3.getImage());
												centercards.add(image3);
												centerX(centerPane);

												pane2.getChildren().remove(0);

												black.remove(0);
												backX(pane2);
												cardsPlayer2.remove(image3);
												if (image3.getId().equals("skip") || image3.getId().equals("reverse")) {
													int z = 0;
													for (Card image4 : cardsPlayer2) {
														if (image4.getId().equals(image3.getId())
																|| image4.getColor().equals(image3.getColor())) {
															centerPane.getChildren().add(image4.getImage());
															centercards.add(image4);
															centerX(centerPane);

															pane2.getChildren().remove(0);
															black.remove(0);
															backX(pane2);
															cardsPlayer2.remove(image4);
															if (image4.getId().equals("skip")
																	|| image4.getId().equals("reverse")) {
																int c = 0;
																for (Card image5 : cardsPlayer2) {
																	if (image5.getId().equals(image4.getId()) || image5
																			.getColor().equals(image4.getColor())) {
																		centerPane.getChildren().add(image5.getImage());
																		centercards.add(image5);
																		centerX(centerPane);

																		pane2.getChildren().remove(0);
																		black.remove(0);
																		backX(pane2);
																		cardsPlayer2.remove(image5);
																		if (image5.getId().equals("skip")
																				|| image5.getId().equals("reverse")) {
																			int v = 0;
																			for (Card image6 : cardsPlayer2) {
																				if (image6.getId()
																						.equals(image5.getId())
																						|| image6.getColor().equals(
																								image5.getColor())) {
																					centerPane.getChildren()
																							.add(image6.getImage());
																					centercards.add(image6);
																					centerX(centerPane);

																					pane2.getChildren().remove(0);
																					black.remove(0);
																					backX(pane2);
																					cardsPlayer2.remove(image6);
																					break;
																				} else if (v >= cardsPlayer2.size()
																						- 1) {
																					black.add(new ImageView(
																							Card.backImage));
																					pane2.getChildren().add(black
																							.get(black.size() - 1));
																					backX(pane2);
																					cardsPlayer2
																							.add(CardDeck.cards.get(0));
																					CardDeck.cards.remove(0);
																					break;
																				}
																				v++;
																			}

																		} else if (image2.getId().equals("picker")) {
																			state = "addtwo";
																		}

																		break;
																	} else if (c >= cardsPlayer2.size() - 1) {
																		black.add(new ImageView(Card.backImage));
																		pane2.getChildren()
																				.add(black.get(black.size() - 1));
																		backX(pane2);
																		cardsPlayer2.add(CardDeck.cards.get(0));
																		CardDeck.cards.remove(0);
																		break;
																	}
																	c++;
																}

															} else if (image2.getId().equals("picker")) {
																state = "addtwo";
															}
															break;
														} else if (z >= cardsPlayer2.size() - 1) {
															black.add(new ImageView(Card.backImage));
															pane2.getChildren().add(black.get(black.size() - 1));
															backX(pane2);
															cardsPlayer2.add(CardDeck.cards.get(0));
															CardDeck.cards.remove(0);
															break;
														}
														z++;
													}

												} else if (image2.getId().equals("picker")) {
													state = "addtwo";
												}
												break;
											} else if (y >= cardsPlayer2.size() - 1) {
												black.add(new ImageView(Card.backImage));
												pane2.getChildren().add(black.get(black.size() - 1));
												backX(pane2);
												cardsPlayer2.add(CardDeck.cards.get(0));
												CardDeck.cards.remove(0);
												break;
											}
											y++;
										}

									} else if (image2.getId().equals("picker")) {
										state = "addtwo";
									}
									break;
								} else if (x >= cardsPlayer2.size() - 1) {
									black.add(new ImageView(Card.backImage));
									pane2.getChildren().add(black.get(black.size() - 1));
									backX(pane2);
									cardsPlayer2.add(CardDeck.cards.get(0));
									CardDeck.cards.remove(0);
									break;
								}
								x++;

							}
						}

					}

				}

				if (state.equals("wildcolor")) {
					label.setText(Card.COLORS[rand.nextInt(3)]);
					label.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 40));
					if (label.getText().equals("red")) {
						label.setTextFill(Color.RED);
					}
					if (label.getText().equals("blue")) {
						label.setTextFill(Color.BLUE);
					}
					if (label.getText().equals("green")) {
						label.setTextFill(Color.GREEN);
					}
					if (label.getText().equals("yellow")) {
						label.setTextFill(Color.YELLOW);
					}
					label.setAlignment(Pos.CENTER);

					pane3.getChildren().add(label);

				} else if (state.equals("wildpicker")) {
					for (int i = 0; i < 4; i++) {
						pane1.getChildren().add(addcards.get(i).getImage());
						CardDeck.usedcards.add(addcards.get(i));
						cardX(pane1);
						cardsPlayer1.add(addcards.get(i));

						addcards.remove(i);
					}

					label.setText(Card.COLORS[rand.nextInt(3)]);
					label.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 40));
					if (label.getText().equals("red")) {
						label.setTextFill(Color.RED);
					}
					if (label.getText().equals("blue")) {
						label.setTextFill(Color.BLUE);
					}
					if (label.getText().equals("green")) {
						label.setTextFill(Color.GREEN);
					}
					if (label.getText().equals("yellow")) {
						label.setTextFill(Color.YELLOW);
					}
					label.setAlignment(Pos.CENTER);

					pane3.getChildren().add(label);

				} else if (state.equals("change color")) {

					for (Button button : buttons) {
						pane3.getChildren().add(button);
					}
					action(pane3, buttons, centerPane, pane2);
				} else if (state.equals("pick four")) {
					for (Button button : buttons) {
						pane3.getChildren().add(button);
					}
					action(pane3, buttons, centerPane, pane2);
					for (int i = 0; i < 4; i++) {
						black.add(new ImageView(Card.backImage));
						pane2.getChildren().add(black.get(black.size() - 1));
						backX(pane2);
						cardsPlayer2.add(CardDeck.cards.get(i));

						CardDeck.cards.remove(i);
					}

				} else if (state.equals("picker")) {
					for (int i = 0; i < 2; i++) {
						black.add(new ImageView(Card.backImage));
						pane2.getChildren().add(black.get(black.size() - 1));
						backX(pane2);
						cardsPlayer2.add(CardDeck.cards.get(i));

						CardDeck.cards.remove(i);
					}

				} else if (state.equals("addtwo")) {
					for (int i = 0; i < 2; i++) {
						pane1.getChildren().add(addcards.get(i).getImage());
						CardDeck.usedcards.add(addcards.get(i));
						cardX(pane1);
						cardsPlayer1.add(addcards.get(i));

						addcards.remove(i);
					}
				}
				if(centercards.size()>8) {
					for(int i =0; i<4;i++) {
						centerPane.getChildren().remove(0);
						centercards.remove(0);
						centerX( centerPane);
					}
					
				}
				cardsPlayer1.remove(img);
				if (cardsPlayer1.isEmpty()) {
					SecondStage second = new SecondStage();
					second.x.setText("you win\n       ");
					st.close();
					second.ok.setOnAction(t -> {

						second.close();
						primaryStage.show();

					});

				} else if (cardsPlayer2.isEmpty()) {
					SecondStage second = new SecondStage();
					second.x.setText("you lose\n       ");
					st.close();
					second.ok.setOnAction(t -> {

						second.close();
						primaryStage.show();

					});

				}

			}

		};

		for (Card image : cardsPlayer1) {
			image.getImage().addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
		}
		for (Card image : addcards) {
			image.getImage().addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
		}

		draw(pane1, button, eventHandler, centerPane, pane2);

	}

	private void action(VBox pane3, Button[] buttons, Pane centerPane, Pane pane2) {

		buttons[0].setOnAction(e -> {
			String state = "";
			Card card = null;
			for (Card image : cardsPlayer2) {
				int x = 0;
				if (image.getColor().equals("red")) {
					centerPane.getChildren().add(image.getImage());
					centercards.add(image);
					centerX(centerPane);
					pane2.getChildren().remove(0);
					black.remove(0);
					backX(pane2);
					card = image;
					state = "remove";
					break;
				} else if (x >= cardsPlayer2.size() - 1) {
					state = "add";
					black.add(new ImageView(Card.backImage));
					pane2.getChildren().add(black.get(black.size() - 1));
					backX(pane2);

					break;
				}
				x++;
			}

			if (state.equals("remove")) {
				cardsPlayer2.remove(card);

			} else if (state.equals("add")) {
				cardsPlayer2.add(CardDeck.cards.get(0));
				CardDeck.cards.remove(0);

			}

			pane3.getChildren().removeAll(buttons[0], buttons[1], buttons[2], buttons[3]);

		});

		buttons[1].setOnAction(e -> {
			String state = "";
			Card card = null;
			int x = 0;
			for (Card image : cardsPlayer2) {
				if (image.getColor().equals("blue")) {
					centerPane.getChildren().add(image.getImage());
					centercards.add(image);
					centerX(centerPane);
					pane2.getChildren().remove(0);
					black.remove(0);
					backX(pane2);
					card = image;
					state = "remove";
					break;
				} else if (x >= cardsPlayer2.size() - 1) {
					state = "add";
					black.add(new ImageView(Card.backImage));
					pane2.getChildren().add(black.get(black.size() - 1));
					backX(pane2);
					CardDeck.cards.remove(0);
					break;
				}
				x++;
			}
			if (state.equals("remove")) {
				cardsPlayer2.remove(card);

			} else if (state.equals("add")) {
				cardsPlayer2.add(CardDeck.cards.get(0));
				CardDeck.cards.remove(0);

			}
			pane3.getChildren().removeAll(buttons[0], buttons[1], buttons[2], buttons[3]);
		});
		buttons[2].setOnAction(e -> {
			String state = "";
			Card card = null;
			int x = 0;
			for (Card image : cardsPlayer2) {
				if (image.getColor().equals("green")) {
					centerPane.getChildren().add(image.getImage());
					centercards.add(image);
					centerX(centerPane);
					pane2.getChildren().remove(0);
					black.remove(0);
					backX(pane2);
					card = image;
					state = "remove";
					break;
				} else if (x >= cardsPlayer2.size() - 1) {
					state = "add";
					black.add(new ImageView(Card.backImage));
					pane2.getChildren().add(black.get(black.size() - 1));
					backX(pane2);
					CardDeck.cards.remove(0);
					break;
				}
				x++;
			}
			if (state.equals("remove")) {
				cardsPlayer2.remove(card);

			} else if (state.equals("add")) {
				cardsPlayer2.add(CardDeck.cards.get(0));
				CardDeck.cards.remove(0);

			}
			pane3.getChildren().removeAll(buttons[0], buttons[1], buttons[2], buttons[3]);
		});
		buttons[3].setOnAction(e -> {
			String state = "";
			Card card = null;
			int x = 0;
			for (Card image : cardsPlayer2) {
				if (image.getColor().equals("yellow")) {
					centerPane.getChildren().add(image.getImage());
					centercards.add(image);
					centerX(centerPane);
					pane2.getChildren().remove(0);
					black.remove(0);
					backX(pane2);
					card = image;
					state = "remove";
					break;
				} else if (x >= cardsPlayer2.size() - 1) {
					state = "add";
					black.add(new ImageView(Card.backImage));
					pane2.getChildren().add(black.get(black.size() - 1));
					backX(pane2);
					CardDeck.cards.remove(0);
					break;
				}
				x++;
			}
			if (state.equals("remove")) {
				cardsPlayer2.remove(card);

			} else if (state.equals("add")) {
				cardsPlayer2.add(CardDeck.cards.get(0));
				CardDeck.cards.remove(0);

			}
			pane3.getChildren().removeAll(buttons[0], buttons[1], buttons[2], buttons[3]);
		});

	}

	private void draw(Pane pane1, Button button, EventHandler<MouseEvent> eventHandler, Pane centerPane, Pane pane2) {

		button.setOnAction(e -> {
			pane1.getChildren().add(CardDeck.cards.get(0).getImage());
			CardDeck.usedcards.add(CardDeck.cards.get(0));
			cardX(pane1);

			cardsPlayer1.add(CardDeck.cards.get(0));
			CardDeck.cards.remove(0);
			String state = "";
			int x = 0;
			for (Card image2 : cardsPlayer2) {
				if (!centercards.isEmpty()) {

					if (image2.getId().equals(centercards.get(centercards.size() - 1).getId())
							|| image2.getColor().equals(centercards.get(centercards.size() - 1).getColor())) {
						centerPane.getChildren().add(image2.getImage());
						centercards.add(image2);
						centerX(centerPane);
						pane2.getChildren().remove(0);
						black.remove(0);
						backX(pane2);
						cardsPlayer2.remove(image2);
						if (image2.getId().equals("skip") || image2.getId().equals("reverse")) {
							int y = 0;
							for (Card image3 : cardsPlayer2) {
								if (image3.getId().equals(image2.getId())
										|| image3.getColor().equals(image2.getColor())) {
									centerPane.getChildren().add(image3.getImage());
									centercards.add(image3);
									centerX(centerPane);
									pane2.getChildren().remove(0);
									black.remove(0);
									backX(pane2);
									cardsPlayer2.remove(image3);
									if (image3.getId().equals("skip") || image3.getId().equals("reverse")) {
										int z = 0;
										for (Card image4 : cardsPlayer2) {
											if (image4.getId().equals(image3.getId())
													|| image4.getColor().equals(image3.getColor())) {
												centerPane.getChildren().add(image4.getImage());
												centercards.add(image4);
												centerX(centerPane);
												pane2.getChildren().remove(0);
												black.remove(0);
												backX(pane2);
												cardsPlayer2.remove(image4);
												if (image4.getId().equals("skip") || image4.getId().equals("reverse")) {
													int c = 0;
													for (Card image5 : cardsPlayer2) {
														if (image5.getId().equals(image4.getId())
																|| image5.getColor().equals(image4.getColor())) {
															centerPane.getChildren().add(image5.getImage());
															centercards.add(image5);
															centerX(centerPane);
															pane2.getChildren().remove(0);
															black.remove(0);
															backX(pane2);
															cardsPlayer2.remove(image5);
															if (image5.getId().equals("skip")
																	|| image5.getId().equals("reverse")) {
																int v = 0;
																for (Card image6 : cardsPlayer2) {
																	if (image6.getId().equals(image5.getId()) || image6
																			.getColor().equals(image5.getColor())) {
																		centerPane.getChildren().add(image6.getImage());
																		centercards.add(image6);
																		centerX(centerPane);
																		pane2.getChildren().remove(0);
																		black.remove(0);
																		backX(pane2);
																		cardsPlayer2.remove(image6);
																		break;
																	} else if (v >= cardsPlayer2.size() - 1) {
																		black.add(new ImageView(Card.backImage));
																		pane2.getChildren()
																				.add(black.get(black.size() - 1));
																		backX(pane2);
																		cardsPlayer2.add(CardDeck.cards.get(0));
																		CardDeck.cards.remove(0);
																		break;
																	}
																	v++;
																}

															} else if (image2.getId().equals("picker")) {
																state = "addtwo";
															}

															break;
														} else if (c >= cardsPlayer2.size() - 1) {
															black.add(new ImageView(Card.backImage));
															pane2.getChildren().add(black.get(black.size() - 1));
															backX(pane2);
															cardsPlayer2.add(CardDeck.cards.get(0));
															CardDeck.cards.remove(0);
															break;
														}
														c++;
													}

												} else if (image2.getId().equals("picker")) {
													state = "addtwo";
												}
												break;
											} else if (z >= cardsPlayer2.size() - 1) {
												black.add(new ImageView(Card.backImage));
												pane2.getChildren().add(black.get(black.size() - 1));
												backX(pane2);
												cardsPlayer2.add(CardDeck.cards.get(0));
												CardDeck.cards.remove(0);
												break;
											}
											z++;
										}

									} else if (image2.getId().equals("picker")) {
										state = "addtwo";
									}
									break;
								} else if (y >= cardsPlayer2.size() - 1) {
									black.add(new ImageView(Card.backImage));
									pane2.getChildren().add(black.get(black.size() - 1));
									backX(pane2);
									cardsPlayer2.add(CardDeck.cards.get(0));
									CardDeck.cards.remove(0);
									break;
								}
								y++;
							}

						} else if (image2.getId().equals("picker")) {
							state = "addtwo";
						}
						break;
					} else if (x >= cardsPlayer2.size() - 1) {
						black.add(new ImageView(Card.backImage));
						pane2.getChildren().add(black.get(black.size() - 1));
						backX(pane2);
						cardsPlayer2.add(CardDeck.cards.get(0));
						CardDeck.cards.remove(0);
						break;
					}
					x++;

				}
			}
			if (state.equals("addtwo")) {
				for (int i = 0; i < 2; i++) {
					pane1.getChildren().add(addcards.get(i).getImage());
					CardDeck.usedcards.add(addcards.get(i));
					cardX(pane1);
					cardsPlayer1.add(addcards.get(i));
					// CardDeck.usedcards.add(CardDeck.cards.get(i));
					addcards.remove(i);
				}
			}

			for (Card image : cardsPlayer1) {
				image.getImage().addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
			}
		});

	}

	private void cardX(Pane pane1) {

		int i = 0;
		for (Card image : CardDeck.usedcards) {
			if (i != 0) {

				image.getImage().setX(
						CardDeck.usedcards.get(i - 1).getImage().getX() + pane1.getWidth() / CardDeck.usedcards.size());
			} else {
				image.getImage().setX(0);
			}
			i++;
		}

	}

	private void backX(Pane pane2) {

		int i = 0;
		for (ImageView image : black) {
			if (i != 0) {

				image.setX(black.get(i - 1).getX() + pane2.getWidth() / black.size());
			} else {
				image.setX(0);
			}
			i++;
		}

	}

	private void centerX(Pane centerPane) {

		int i = 0;
		for (Card image : centercards) {
			if (i != 0) {

				image.getImage()
						.setX(centercards.get(i - 1).getImage().getX() + centerPane.getWidth() / centercards.size());
			} else {
				image.getImage().setX(0);
			}
			i++;
		}

	}

}

class SecondStage extends Stage {
	Label x = new Label();
	VBox y = new VBox();
	Button ok = new Button("OK");

	SecondStage() {
		x.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 40));
		x.setTextFill(Color.BLACK);
		ok.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 22));
		ok.setPrefSize(100, 50);
		ok.setStyle("-fx-border-color: blue");
		ok.setStyle("-fx-background-color: Black");
		ok.setTextFill(Color.ANTIQUEWHITE);
		y.setStyle("-fx-background-Color: Red ");
		y.getChildren().addAll(x, ok);
		this.setScene(new Scene(y, 200, 200));
		this.show();
		y.setAlignment(Pos.CENTER);

	}

}
