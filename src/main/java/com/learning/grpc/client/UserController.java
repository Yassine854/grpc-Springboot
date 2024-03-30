package com.learning.grpc.client;

import com.google.protobuf.Empty;
import com.learning.grpc.UserObj;
import com.learning.grpc.UserServiceGrpc;
import com.learning.grpc.UserServiceGrpc.UserServiceBlockingStub;
import com.learning.grpc.UsersResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Value("localhost")
    private String grpcServerHost;

    @Value("8798")
    private int grpcServerPort;

    @GetMapping
    public ModelAndView getAllUsers(Model model) {
        List<UserDTO> userList = new ArrayList<>();

        // Establish a gRPC channel to communicate with the server
        ManagedChannel channel = ManagedChannelBuilder.forAddress(grpcServerHost, grpcServerPort)
                .usePlaintext()
                .build();

        // Create a stub for the UserService gRPC service
        UserServiceBlockingStub stub = UserServiceGrpc.newBlockingStub(channel);

        // Call the getAllUsers gRPC method to retrieve users from the server
        UsersResponse resp = stub.getAllUsers(Empty.getDefaultInstance());
        List<UserObj> users = resp.getUsersList();

        // Convert UserObj to UserDTO
        for (UserObj userObj : users) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(userObj.getId());
            userDTO.setName(userObj.getName());
            userDTO.setAge(userObj.getAge());
            userList.add(userDTO);
        }

        // Shutdown the gRPC channel
        channel.shutdown();

        // Add the userList to the model
        model.addAttribute("users", userList);

        // Return the ModelAndView object with the view name
        return new ModelAndView("users");
    }

    // DTO class for User
    public static class UserDTO {
        private int id;
        private String name;
        private int age;

        // getters and setters

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
